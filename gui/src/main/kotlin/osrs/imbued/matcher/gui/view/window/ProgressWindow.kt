package osrs.imbued.matcher.gui.view.window

import javafx.scene.layout.Priority
import tornadofx.*

/**
 * The window used when to display an async task's progress.
 */
class ProgressWindow : View("Operation Progress") {

    override val root = vbox {
        prefWidth = 300.0
        paddingAll = 5.0

        progressbar {
            paddingBottom = 5.0
            hgrow = Priority.ALWAYS
            prefWidth = 290.0
        }

        text("Please Wait...")
    }
}