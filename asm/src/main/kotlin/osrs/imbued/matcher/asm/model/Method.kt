package osrs.imbued.matcher.asm.model

import org.objectweb.asm.Type
import org.objectweb.asm.tree.MethodNode

/**
 * Represents an ASM loaded method node.
 */
class Method(val group: ClassGroup, val clazz: Class, val node: MethodNode) {

    val name get() = node.name

    val desc get() = node.desc

    val access get() = node.access

    val type get() = Type.getMethodType(this.desc)
}