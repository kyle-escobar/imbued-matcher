package osrs.imbued.matcher.gui.controller

import javafx.stage.FileChooser
import org.tinylog.kotlin.Logger
import osrs.imbued.matcher.gui.util.runProgressTask
import osrs.imbued.matcher.gui.view.window.NewProjectWindow
import osrs.imbued.matcher.matcher.MatchManager
import tornadofx.Controller
import tornadofx.FileChooserMode
import tornadofx.chooseFile
import java.nio.file.Files
import kotlin.system.exitProcess

/**
 * The controller class for the top menubar.
 */
class MenuController : Controller() {

    /**
     * Controllers
     */
    private val projectController: ProjectController by inject()

    /**
     * Exits the application
     */
    fun exitApplication() {
        Logger.info("Exiting application.")
        exitProcess(0)
    }

    /**
     * Opens the new project window.
     */
    fun newProject() {
        Logger.info("Opening new project window.")
        find(NewProjectWindow::class).openModal()
    }

    /**
     * Saves the current project to a binary project file.
     */
    fun saveProject() {
        val files = chooseFile("Save Project As", mode = FileChooserMode.Save, filters = arrayOf(FileChooser.ExtensionFilter("Imbued", "*.imbued")))
        if(files.isEmpty()) {
            return
        }

        val file = files.first()

        projectController.matchManager?.projectName = file.nameWithoutExtension
        val bytes = projectController.matchManager?.toProjectFileBytes()

        Logger.info("Saving project to file: '${file.name}'.")

        runProgressTask("Saving Project...") {
            it.onComplete = {
                Logger.info("Completed saving project.")
            }

            it.setProgress(0.1)
            @Suppress("BlockingMethodInNonBlockingContext")
            Files.newOutputStream(file.toPath()).use { writer ->
                writer.write(bytes!!)
            }
            it.setProgress(1.0)
        }
    }

    fun openProject() {
        val files = chooseFile("Open Project", mode = FileChooserMode.Single, filters = arrayOf(FileChooser.ExtensionFilter("Imbued", "*.imbued")))
        if(files.isEmpty()) {
            return
        }

        val file = files.first()
        val bytes = Files.newInputStream(file.toPath()).use { reader ->
            return@use reader.readAllBytes()
        }

        val matchManager = MatchManager()
        matchManager.initFromProjectFile(bytes)
    }
}