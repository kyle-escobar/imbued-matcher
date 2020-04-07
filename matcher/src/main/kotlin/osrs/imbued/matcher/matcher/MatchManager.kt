package osrs.imbued.matcher.matcher

import com.fasterxml.jackson.databind.ObjectMapper
import org.msgpack.jackson.dataformat.MessagePackFactory
import org.tinylog.kotlin.Logger
import osrs.imbued.matcher.asm.model.ClassGroup
import osrs.imbued.matcher.common.Progress
import osrs.imbued.matcher.common.getChecksum
import osrs.imbued.matcher.matcher.project.ProjectFile
import osrs.imbued.matcher.matcher.project.ProjectJarFile
import osrs.imbued.matcher.matcher.project.ProjectJarType
import java.io.File

/**
 * The main matcher project object.
 */
class MatchManager {

    /**
     * The name of the project.
     * This is set when the project is firstly saved.
     */
    var projectName: String = ""

    /**
     * The input jar file
     */
    lateinit var inputJar: File

    /**
     * The reference jar file
     */
    lateinit var referenceJar: File

    /**
     * The input jar class group.
     */
    lateinit var inputGroup: ClassGroup

    /**
     * The reference jar class group.
     */
    lateinit var referenceGroup: ClassGroup

    /*************************************************
     * The project save file. Not initialized.
     *************************************************/
    var projectFile: ProjectFile? = null

    /**
     * Initializes the project from jar files.
     * @param inputJar The input jar file (new jar)
     * @param referenceJar The reference jar file (old jar)
     */
    fun initFromFiles(inputJar: File, referenceJar: File, progress: Progress) {
        Logger.info("Initializing project from files.")

        this.inputJar = inputJar
        this.referenceJar = referenceJar

        this.inputGroup = ClassGroup()
        this.referenceGroup = ClassGroup()

        inputGroup.extractJar(inputJar, progress)
        referenceGroup.extractJar(referenceJar, progress)

        progress.complete()
        Logger.info("Completed project initialization.")
    }

    /**
     * Initializes the project a project file [ByteArray]
     * @param bytes The bytes of the project file.
     */
    fun initFromProjectFile(bytes: ByteArray) {
        Logger.info("Initializing project from project file.")

        try {
            val objectMapper = ObjectMapper(MessagePackFactory())
            this.projectFile = objectMapper.readValue(bytes, ProjectFile::class.java)

            /**
             * These are not valid files.
             * Because the project file packs the original JAR file bytes, we just need to
             * reference them for the GUI.
             */
            this.inputJar = File(projectFile!!.inputJar.fileName)
            this.referenceJar = File(projectFile!!.referenceJar.fileName)

            this.inputGroup = ClassGroup()
            this.referenceGroup = ClassGroup()

            /**
             * Load the bytes
             */
        } catch(e : Exception) {
            Logger.error(e) { "Failed to open project file. File may be corrupt. : $e" }
        }
    }

    /**
     * Converts the relevant fields for a project save to a packed binary
     * file which can be saved as a project file.
     */
    fun toProjectFileBytes(): ByteArray {
        if(this.projectFile == null) {
            this.buildProjectFile()
        }

        val objectMapper = ObjectMapper(MessagePackFactory())
        return objectMapper.writeValueAsBytes(projectFile)
    }

    /**
     * Builds an initializes the [projectFile]
     */
    private fun buildProjectFile() {
        val inputJarFile = ProjectJarFile(inputJar.nameWithoutExtension, inputJar.getChecksum(), ProjectJarType.INPUT)
        val referenceJarFile = ProjectJarFile(referenceJar.nameWithoutExtension, referenceJar.getChecksum(), ProjectJarType.REFERENCE)

        this.projectFile = ProjectFile(
            projectName = this.projectName,
            inputJar = inputJarFile,
            referenceJar = referenceJarFile,
            inputGroupBytes = this.inputGroup.toByteArray(),
            referenceGroupBytes = this.referenceGroup.toByteArray()
        )
    }

    companion object {
        /**
         * The project file extension.
         */
        private const val PROJECT_FILE_EXTENSION = ".imbued"
    }
}