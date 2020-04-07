package osrs.imbued.matcher.matcher.project

/**
 * Represents the project file data object which gets
 * converted to a packed binary.
 */
class ProjectFile {
    lateinit var projectName: String
    lateinit var inputJar: ProjectJarFile
    lateinit var referenceJar: ProjectJarFile
    lateinit var inputGroupBytes: List<ByteArray>
    lateinit var referenceGroupBytes: List<ByteArray>
}