package osrs.imbued.matcher.gui.controller

import osrs.imbued.matcher.gui.model.ProjectModel
import osrs.imbued.matcher.matcher.Matcher
import tornadofx.Controller

/**
 * Responsible for holding project references.
 */
class ProjectController : Controller() {

    val projectModel = ProjectModel()

    /**
     * The loaded matcher project.
     */
    var project: Matcher? = null

    /**
     * Initializes all the bindings after creating or loading
     * a project.
     */
    fun initProject() {
        projectModel.inputGroup.value = project?.inputGroup
        projectModel.referenceGroup.value = project?.referenceGroup
    }
}