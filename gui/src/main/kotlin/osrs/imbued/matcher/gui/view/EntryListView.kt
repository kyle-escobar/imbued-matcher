package osrs.imbued.matcher.gui.view

import javafx.geometry.Orientation
import javafx.scene.layout.Priority
import tornadofx.*

/**
 * Represents the left-hand class, method, and field view.
 */
class EntryListView : View() {

    override val root = splitpane(Orientation.VERTICAL) {
        prefWidth = 175.0
        minWidth = 150.0
        hgrow = Priority.ALWAYS

        listview<Any> {}
        listview<Any> {}
        listview<Any> {}

        setDividerPosition(0, 0.6)
        setDividerPosition(1, 0.85)
    }
}