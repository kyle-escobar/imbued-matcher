package osrs.imbued.matcher.matcher

import org.tinylog.kotlin.Logger
import osrs.imbued.matcher.asm.model.ClassGroup
import java.io.File

/**
 * The main matcher project object.
 */
class Matcher {

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
    val inputGroup = ClassGroup()

    /**
     * The reference jar class group.
     */
    val referenceGroup = ClassGroup()

    /**
     * Initializes the project from jar files.
     * @param inputJar The input jar file (new jar)
     * @param referenceJar The reference jar file (old jar)
     */
    fun initFromFiles(inputJar: File, referenceJar: File) {
        Logger.info("Initializing project from files.")

        this.inputJar = inputJar
        this.referenceJar = referenceJar

        inputGroup.extractJar(inputJar)
        referenceGroup.extractJar(referenceJar)
    }
}