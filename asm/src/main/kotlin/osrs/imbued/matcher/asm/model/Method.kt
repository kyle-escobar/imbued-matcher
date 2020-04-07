package osrs.imbued.matcher.asm.model

import org.objectweb.asm.Type
import org.objectweb.asm.tree.MethodNode
import osrs.imbued.matcher.asm.Matchable

/**
 * Represents an ASM loaded method node.
 */
class Method(val group: ClassGroup, val clazz: Class, val node: MethodNode) : Matchable<Method>() {

    companion object {
        const val CONSTRUCTOR_NAME = "<init>"
        const val INITIALIZER_NAME = "<clinit>"
    }

    val name get() = node.name

    val desc get() = node.desc

    val access get() = node.access

    val type get() = Type.getMethodType(this.desc)

    val arguments = type.argumentTypes.asList()

    val returnType get() = type.returnType

    val signature = name to arguments

    val mark = name to type

    val id = Triple(clazz.type, name, type)

    val isInitializer: Boolean get() {
        return name == INITIALIZER_NAME
    }

    val isConstructor: Boolean get() {
        return name == CONSTRUCTOR_NAME
    }

    val instructions get() = node.instructions.iterator().asSequence()
        .map { Instruction(group, clazz, this, it) }

    override fun toString(): String = "$clazz.$name$desc"
}