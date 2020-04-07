package osrs.imbued.matcher.gui.controller

import osrs.imbued.matcher.matcher.Matcher
import tornadofx.Controller

/**
 * Responsible for holding project references.
 */
class ProjectController : Controller() {

    /**
     * The loaded matcher project.
     */
    var project: Matcher? = null
}