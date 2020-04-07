package osrs.imbued.matcher.asm.model

import org.objectweb.asm.ClassReader
import org.objectweb.asm.tree.ClassNode
import java.io.File
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
    fun extractJar(file: File): Collection<ClassNode> {
        val list = mutableListOf<ClassNode>()

        JarFile(file).use { jar ->
            jar.stream().iterator().asSequence()
                .filter { it.name.endsWith(".class") }
                .forEach {
                    val node = ClassNode()
                    val reader = ClassReader(jar.getInputStream(it))
                    reader.accept(node, ClassReader.SKIP_FRAMES or ClassReader.SKIP_DEBUG)
                    list.add(node)
                }
        }

        return list
    }
}