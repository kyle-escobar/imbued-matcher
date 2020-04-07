package osrs.imbued.matcher.common

/**
 * Represents a progress callback
 */
interface Progress {

    /**
     * The current progress amount
     */
    var currentProgress: Double

    /**
     * The status text of the progress window
     */
    var statusText: String

    /**
     * The action to be performed on completion.
     */
    var onComplete: () -> Unit

    /**
     * Increase the progress
     */
    fun setProgress(progress: Double)

    /**
     * Invoked when the task completes.
     */
    fun complete()
}