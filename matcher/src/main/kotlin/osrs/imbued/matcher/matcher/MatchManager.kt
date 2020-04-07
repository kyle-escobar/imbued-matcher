package osrs.imbued.matcher.matcher

import com.fasterxml.jackson.databind.ObjectMapper
import org.msgpack.jackson.dataformat.MessagePackFactory
import org.tinylog.kotlin.Logger
import osrs.imbued.matcher.asm.model.ClassGroup
import osrs.imbued.matcher.common.Progress
import osrs.imbued.matcher.matcher.project.ProjectFile
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
     * Converts the relevant fields for a project save to a packed binary
     * file which can be saved as a project file.
     */
    fun toByteArray(): ByteArray {
        val project = ProjectFile(
            projectName = this.projectName,
            inputGroupBytes = this.inputGroup.toByteArray(),
            referenceGroupBytes = this.referenceGroup.toByteArray()
        )

        val objectMapper = ObjectMapper(MessagePackFactory())
        return objectMapper.writeValueAsBytes(project)
    }

    companion object {
        /**
         * The project file extension.
         */
        private const val PROJECT_FILE_EXTENSION = ".imbued"
    }
}