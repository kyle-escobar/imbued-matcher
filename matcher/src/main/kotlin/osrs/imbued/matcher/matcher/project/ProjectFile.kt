package osrs.imbued.matcher.matcher.project

/**
 * Represents the project file data object which gets
 * converted to a packed binary.
 */
data class ProjectFile(
    val projectName: String,
    val inputGroupBytes: ByteArray,
    val referenceGroupBytes: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProjectFile

        if (projectName != other.projectName) return false
        if (!inputGroupBytes.contentEquals(other.inputGroupBytes)) return false
        if (!referenceGroupBytes.contentEquals(other.referenceGroupBytes)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = projectName.hashCode()
        result = 31 * result + inputGroupBytes.contentHashCode()
        result = 31 * result + referenceGroupBytes.contentHashCode()
        return result
    }
}