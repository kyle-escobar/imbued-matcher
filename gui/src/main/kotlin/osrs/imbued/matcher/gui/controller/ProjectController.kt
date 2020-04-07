package osrs.imbued.matcher.gui.controller

import osrs.imbued.matcher.gui.model.ProjectModel
import osrs.imbued.matcher.matcher.MatchManager
import tornadofx.Controller

/**
 * Responsible for holding project references.
 */
class ProjectController : Controller() {

    val projectModel = ProjectModel()

    /**
     * The loaded matcher project.
     */
    var matchManager: MatchManager? = null

    /**
     * Initializes all the bindings after creating or loading
     * a project.
     */
    fun initProject() {
        projectModel.inputGroup.value = matchManager?.inputGroup
        projectModel.referenceGroup.value = matchManager?.referenceGroup
    }
}