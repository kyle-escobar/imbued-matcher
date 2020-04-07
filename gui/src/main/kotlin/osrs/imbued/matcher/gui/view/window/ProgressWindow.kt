package osrs.imbued.matcher.gui.view.window

import javafx.scene.control.ProgressBar
import javafx.scene.layout.Priority
import javafx.scene.text.Text
import tornadofx.*

/**
 * The window used when to display an async task's progress.
 */
class ProgressWindow : View("Operation Progress") {

    /**
     * The progress bar component.
     */
    private var progressbar: ProgressBar by singleAssign()
    private var statusText: Text by singleAssign()

    override val root = vbox {
        prefWidth = 300.0
        paddingAll = 5.0

        progressbar {
            paddingBottom = 5.0
            hgrow = Priority.ALWAYS
            prefWidth = 290.0
            progress = 0.0
            progressbar = this
        }

        text("Please Wait...") {
            statusText = this
        }
    }

    /**
     * Updates the progress bar amount.
     */
    fun updateProgress(progress: Double) {
        this.progressbar.progress = progress
    }

    /**
     * Updates the status text.
     */
    fun updateStatus(text: String) {
        this.statusText.text = text
    }
}