package osrs.imbued.matcher.gui.view

import javafx.scene.control.MenuItem
import javafx.scene.layout.BorderPane
import osrs.imbued.matcher.gui.controller.MenuController
import osrs.imbued.matcher.gui.controller.ProjectController
import tornadofx.*

/**
 * The main view window.
 */
class RootView : View("Imbued Matcher") {

    override val root = BorderPane()

    /**
     * Controllers
     */
    private val menuController: MenuController by inject()
    private val projectController: ProjectController by inject()

    /**
     * Initialize the view window.
     */
    init {
        with(root) {
            setPrefSize(1280.0, 900.0)

            top = menubar {
                menu("File") {
                    item("New Project").action { menuController.newProject() }
                    item("Open Project").action { menuController.openProject() }
                    separator()
                    item("Save Project") {
                        action {
                            if(projectController.matchManager == null) return@action
                            menuController.saveProject()
                        }
                    }
                    item("Export Mappings")
                    separator()
                    item("Exit").action { menuController.exitApplication() }
                }
            }

            left = find<EntryListView>().root
        }
    }
}