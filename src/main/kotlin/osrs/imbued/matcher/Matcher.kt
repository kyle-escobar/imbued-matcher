package osrs.imbued.matcher

import org.tinylog.kotlin.Logger
import osrs.imbued.matcher.gui.Gui

/**
 * Main object
 */
object Matcher {

    @JvmStatic
    fun main(args: Array<String>) {
        Logger.info("Initializing...")

        /**
         * Start the GUI
         */
        this.createAndStartGui()
    }

    /**
     * Creates and starts a new GUI instance.
     */
    private fun createAndStartGui() {
        Gui.create().start()
    }
}