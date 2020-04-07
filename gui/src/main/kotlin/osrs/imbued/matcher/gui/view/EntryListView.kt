package osrs.imbued.matcher.gui.view

import javafx.geometry.Orientation
import javafx.scene.control.SelectionMode
import javafx.scene.layout.Priority
import javafx.scene.paint.Color
import javafx.scene.text.Font
import osrs.imbued.matcher.asm.model.Class
import osrs.imbued.matcher.asm.model.ClassGroup
import osrs.imbued.matcher.asm.model.Method
import osrs.imbued.matcher.gui.controller.ProjectController
import tornadofx.*

/**
 * Represents the left-hand class, method, and field view.
 */
class EntryListView : View() {

    /**
     * Controllers
     */
    private val projectController: ProjectController by inject()

    /**
     * Observables
     */
    private val selectedClassEntry = mutableListOf<Class>().asObservable()

    override val root = splitpane(Orientation.VERTICAL) {
        prefWidth = 215.0
        minWidth = 150.0
        hgrow = Priority.ALWAYS

        listview<Class> {
            cellFormat {
                text = it.name
            }

            selectionModel.selectionMode = SelectionMode.SINGLE

            projectController.projectModel.inputGroup.onChange {
                items.setAll(it!!.classes)
            }

            selectedClassEntry.bind(selectionModel.selectedItems) { it }
        }

        listview<Method> {
            cellFormat {
                text = it.clazz.name + "." + it.name + it.desc
            }

            selectionModel.selectionMode = SelectionMode.SINGLE

            selectedClassEntry.onChange {
                if(it.list.isEmpty()) return@onChange
                val clazz = it.list!!.first()
                items.setAll(clazz.methods)
            }
        }
        listview<Any> {}

        setDividerPosition(0, 0.6)
        setDividerPosition(1, 0.85)
    }
}