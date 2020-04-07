package osrs.imbued.matcher.gui.model

import javafx.beans.property.SimpleObjectProperty
import osrs.imbued.matcher.asm.model.ClassGroup
import osrs.imbued.matcher.matcher.MatchManager
import tornadofx.ItemViewModel

class ProjectModel : ItemViewModel<MatchManager>() {

    val inputGroup = bind { SimpleObjectProperty<ClassGroup>() }
    val referenceGroup = bind { SimpleObjectProperty<ClassGroup>() }

}