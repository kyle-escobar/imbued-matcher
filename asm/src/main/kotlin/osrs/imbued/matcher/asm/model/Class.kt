package osrs.imbued.matcher.asm.model

import org.objectweb.asm.Type
import org.objectweb.asm.tree.ClassNode

/**
 * Represents an ASM [ClassNode]
 */
class Class(val group: ClassGroup, val node: ClassNode) {

    val name get() = node.name

    val superName get() = node.superName

    /**
     * Methods
     */
    val methods get() = node.methods.map { Method(group, this, it) }

    /**
     * Fields
     */
    val fields get() = node.fields.map { Field(group, this, it) }

    val access get() = node.access

    val type get() = Type.getObjectType(this.name)

    val superType get() = Type.getObjectType(this.superName)

    val interfaces get() = node.interfaces.map { Type.getObjectType(it) }

    override fun toString(): String = this.name
}