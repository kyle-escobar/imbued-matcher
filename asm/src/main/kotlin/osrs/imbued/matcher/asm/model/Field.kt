package osrs.imbued.matcher.asm.model

import org.objectweb.asm.Type
import org.objectweb.asm.tree.FieldNode
import osrs.imbued.matcher.asm.Matchable

/**
 * Represents an ASM loaded field.
 */
class Field(val group: ClassGroup, val clazz: Class, val node: FieldNode) : Matchable<Field>() {

    val name get() = node.name

    val access get() = node.access

    val desc get() = node.desc

    val type get() = Type.getType(desc)

    val id get() = clazz.type to name

    override fun toString(): String = "$clazz.$name"
}