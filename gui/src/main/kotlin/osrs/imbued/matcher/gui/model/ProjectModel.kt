package osrs.imbued.matcher.gui.model

import javafx.beans.property.SimpleObjectProperty
import osrs.imbued.matcher.asm.model.ClassGroup
import osrs.imbued.matcher.matcher.Matcher
import tornadofx.ItemViewModel

class ProjectModel : ItemViewModel<Matcher>() {

    val inputGroup = bind { SimpleObjectProperty<ClassGroup>() }
    val referenceGroup = bind { SimpleObjectProperty<ClassGroup>() }

}