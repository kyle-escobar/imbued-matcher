package osrs.imbued.matcher.asm.model

import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.tree.ClassNode
import org.paumard.streams.StreamsUtils
import osrs.imbued.matcher.common.Progress
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.ByteBuffer
import java.nio.file.Files
import java.nio.file.Paths
import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarInputStream
import java.util.jar.JarOutputStream

/**
 * Represents an ASM loaded jar file.
 */
class ClassGroup {

    /**
     * List of [Class]s
     */
    val classes = mutableListOf<Class>()

    /**
     * Adds a [ClassNode] to the group.
     * @param node The node to add
     * @return [Boolean] The addition result.
     */
    fun add(node: ClassNode): Boolean {
        return classes.add(Class(this, node))
    }

    /**
     * Extracts each class in a jar file into a [ClassNode] object.
     */
    fun extractJar(file: File, progress: Progress) {
        val jar = JarFile(file)
        val steps = jar.entries().iterator().asSequence().count()
        var step = 0

        val entries = jar.entries()
        while(entries.hasMoreElements()) {
            val entry = entries.nextElement()
            if(entry.name.endsWith(".class")) {
                val node = ClassNode()
                val reader = ClassReader(jar.getInputStream(entry))
                reader.accept(node, ClassReader.SKIP_FRAMES or ClassReader.SKIP_DEBUG)
                this.add(node)

                /**
                 * Update progress
                 */
                step++
                progress.setProgress( progress.currentProgress + (0.5 / (steps / step)))
            }
        }
    }

    /**
     * Adds nodes to the [classes] store from a buffered byte array holding
     * all of the class bytes.
     */
    fun addFromClassBytes(entries: List<ByteArray>) {
        entries.forEach { entry ->
            val node = ClassNode()
            val reader = ClassReader(entry)
            reader.accept(node, ClassReader.SKIP_FRAMES or ClassReader.SKIP_DEBUG)
            this.add(node)
        }
    }

    /**
     * Converts the contents of [classes] to a buffered [ByteArray].
     * This is used to store within a packed
     */
    fun toClassBytesList(): List<ByteArray> {
        val list = mutableListOf<ByteArray>()

        classes.forEach { c ->
            val writer = ClassWriter(ClassWriter.COMPUTE_MAXS)
            c.node.accept(writer)
            list.add(writer.toByteArray())
        }

        return list
    }
}