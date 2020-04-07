package osrs.imbued.matcher.gui.controller

import javafx.scene.control.Alert
import javafx.stage.FileChooser
import org.tinylog.kotlin.Logger
import osrs.imbued.matcher.gui.util.runProgressTask
import osrs.imbued.matcher.gui.view.window.NewProjectWindow
import osrs.imbued.matcher.matcher.Matcher
import tornadofx.Controller
import tornadofx.alert
import tornadofx.asObservable
import tornadofx.chooseFile
import java.io.File

/**
 * The controller for the new project window.
 */
class NewProjectController : Controller() {

    /**
     * The input jars observable table
     */
    val inputJars = mutableListOf<File>().asObservable()
    val referenceJars = mutableListOf<File>().asObservable()

    /**
     * Injected views
     */
    private val newProjectWindow: NewProjectWindow by inject()

    private val projectController: ProjectController by inject()

    /**
     * Opens the file chooser for a given jar type observable store
     */
    fun chooseJar(type: JarType) {
        val target = when(type) {
            JarType.INPUT -> inputJars
            JarType.REFERENCE -> referenceJars
        }

        /**
         * If there is already a jar selected, bitch about it.
         */
        if(target.size > 0) {
            alert(Alert.AlertType.ERROR, "Oh Snap!", "You can not have more than one $type selected!")
            return
        }

        /**
         * Open the file chooser and add the file to the target observable.
         */
        val file = chooseFile("Choose Jar", arrayOf(FileChooser.ExtensionFilter("Jar", "*.jar"))).first()
        target.add(file)
    }

    /**
     * Removes the selected jars give a jar type observable store.
     */
    fun removeJar(type: JarType) {
        val selectionTarget = when(type) {
            JarType.INPUT -> newProjectWindow.selectedInputJars
            JarType.REFERENCE -> newProjectWindow.selectedReferenceJars
        }

        val target = when(type) {
            JarType.INPUT -> inputJars
            JarType.REFERENCE -> referenceJars
        }

        if(selectionTarget.isEmpty()) {
            return
        }

        target.remove(selectionTarget.first())
    }

    /**
     * Resets the controller
     */
    fun reset() {
        inputJars.clear()
        referenceJars.clear()
    }

    /**
     * Creates the project.
     */
    fun createProject() {
        if(inputJars.size == 0 || referenceJars.size == 0) {
            alert(Alert.AlertType.ERROR, "Oh Snap!", "You must select both an input and reference jar file.")
            return
        }

        Logger.info("Creating new project.")
        newProjectWindow.close()

        /**
         * Run the async parallel progress task.
         */
        runProgressTask("Initializing project...") {
            it.onComplete = { this.reset() }

            val matcher = Matcher()
            matcher.initFromFiles(inputJars.first(), referenceJars.first(), it)

            projectController.project = matcher
        }
    }

    /**
     * The jar type.
     */
    enum class JarType {
        INPUT,
        REFERENCE;
    }
}