package osrs.imbued.matcher.gui.view

import javafx.scene.layout.BorderPane
import osrs.imbued.matcher.gui.controller.MenuController
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

    /**
     * Initialize the view window.
     */
    init {
        with(root) {
            setPrefSize(1280.0, 900.0)

            top = menubar {
                menu("File") {
                    item("New Project"). action { menuController.newProject() }
                    item("Open Project")
                    separator()
                    item("Save") { isDisable = true }
                    item("Save As") { isDisable = true }
                    item("Export Mappings") { isDisable = true }
                    separator()
                    item("Exit").action { menuController.exitApplication() }
                }
            }

            left = find<EntryListView>().root
        }
    }
}