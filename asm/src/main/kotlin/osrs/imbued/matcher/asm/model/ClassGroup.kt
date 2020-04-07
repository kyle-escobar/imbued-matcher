package osrs.imbued.matcher.asm.model

import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.tree.ClassNode
import osrs.imbued.matcher.common.Progress
import java.io.ByteArrayOutputStream
import java.io.File
import java.nio.ByteBuffer
import java.util.jar.JarFile

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
     * Converts the contents of [classes] to a buffered [ByteArray].
     * This is used to store within a packed
     */
    fun toByteArray(): ByteArray {
        val outBuf = ByteArrayOutputStream()

        classes.forEach { c ->
            val writer = ClassWriter(ClassWriter.COMPUTE_MAXS)
            c.node.accept(writer)
            outBuf.writeBytes(writer.toByteArray())
        }

        return outBuf.toByteArray()
    }
}