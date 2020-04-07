package osrs.imbued.matcher.asm

/**
 * Represents an ASM node type which is matchable and holds mappings.
 */
abstract class Matchable<T> {

    /**
     * The matched instance
     */
    var match: Matchable<T>? = null

    /**
     * Whether this object has any match.
     */
    fun hasMatch(): Boolean {
        return (match != null)
    }
}