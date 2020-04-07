package osrs.imbued.matcher.asm.model

import org.objectweb.asm.tree.ClassNode

/**
 * Represents an ASM [ClassNode]
 */
class Class(val group: ClassGroup, val node: ClassNode) {

    val name get() = node.name

    val superName get() = node.superName

}