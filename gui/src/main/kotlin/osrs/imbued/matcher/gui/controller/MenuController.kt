package osrs.imbued.matcher.gui.controller

import org.tinylog.kotlin.Logger
import osrs.imbued.matcher.gui.view.window.NewProjectWindow
import tornadofx.Controller
import kotlin.system.exitProcess

/**
 * The controller class for the top menubar.
 */
class MenuController : Controller() {

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
        find<NewProjectWindow>().openWindow()
    }
}