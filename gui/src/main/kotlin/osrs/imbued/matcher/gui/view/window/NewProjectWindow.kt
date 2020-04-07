package osrs.imbued.matcher.gui.view.window

import javafx.geometry.Pos
import javafx.scene.control.SelectionMode
import javafx.scene.layout.Priority
import kotlinx.coroutines.delay
import osrs.imbued.matcher.gui.controller.NewProjectController
import osrs.imbued.matcher.gui.util.runProgressTask
import tornadofx.*
import java.io.File

/**
 * The new project window
 */
class NewProjectWindow : View("New Project") {

    val selectedInputJars = mutableListOf<File>().asObservable()
    val selectedReferenceJars = mutableListOf<File>().asObservable()

    /**
     * Controllers
     */
    private val newProjectController: NewProjectController by inject()

    override val root = form {
        setPrefSize(500.0, 300.0)

        hbox(10) {
            /**
             * Input Jar
             */
            fieldset("Input Jar") {
                listview(newProjectController.inputJars) {
                    prefHeight = 200.0
                    hgrow = Priority.ALWAYS

                    selectionModel.selectionMode = SelectionMode.SINGLE
                    selectedInputJars.bind(selectionModel.selectedItems) { it }

                    cellFormat {
                        text = it.absolutePath
                    }
                }

                hbox(1) {
                    button("Add").action { newProjectController.chooseJar(NewProjectController.JarType.INPUT) }
                    button("Remove").action { newProjectController.removeJar(NewProjectController.JarType.INPUT) }
                }
            }

            /**
             * Reference Jar
             */
            fieldset("Reference Jar") {
                listview(newProjectController.referenceJars) {
                    prefHeight = 200.0
                    hgrow = Priority.ALWAYS

                    selectionModel.selectionMode = SelectionMode.SINGLE
                    selectedReferenceJars.bind(selectionModel.selectedItems) { it }

                    cellFormat {
                        text = it.absolutePath
                    }
                }

                hbox(1) {
                    button("Add").action { newProjectController.chooseJar(NewProjectController.JarType.REFERENCE) }
                    button("Remove").action { newProjectController.removeJar(NewProjectController.JarType.REFERENCE) }
                }
            }
        }

        hbox(5) {
            alignment = Pos.CENTER_RIGHT

            button("Cancel").action {
                newProjectController.reset()
                close()
            }

            button("Create").action {
                newProjectController.createProject()
            }
        }
    }
}