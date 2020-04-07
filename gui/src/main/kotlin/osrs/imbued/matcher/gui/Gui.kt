package osrs.imbued.matcher.gui

import javafx.scene.image.Image
import org.tinylog.kotlin.Logger
import osrs.imbued.matcher.gui.view.RootView
import tornadofx.App
import tornadofx.launch
import tornadofx.setStageIcon

/**
 * Represents the main gui. Responsible for controlling its initialization.
 */
class Gui {

    /**
     * The root application.
     */
    class RootApp : App(RootView::class) {
        init {
            setStageIcon(Image("app-icon.png"))
        }
    }

    /**
     * Starts the main gui application.
     */
    fun start() {
        Logger.info("Starting Gui application.")
        launch<RootApp>()
    }

    companion object {
        /**
         * Creates a new gui object.
         */
        fun create(): Gui = Gui()
    }
}