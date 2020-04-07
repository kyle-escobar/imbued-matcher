package osrs.imbued.matcher.asm.model

import org.objectweb.asm.Type
import org.objectweb.asm.tree.ClassNode

/**
 * Represents an ASM [ClassNode]
 */
class Class(val group: ClassGroup, val node: ClassNode) {

    val name get() = node.name

    val superName get() = node.superName

    val access get() = node.access

    val type get() = Type.getObjectType(this.name)

    /**
     * Methods
     */
    val methods get() = node.methods.map { Method(group, this, it) }
}