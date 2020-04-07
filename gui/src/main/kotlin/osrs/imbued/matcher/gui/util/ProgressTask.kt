package osrs.imbued.matcher.gui.util

import javafx.application.Platform
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import osrs.imbued.matcher.common.Progress
import osrs.imbued.matcher.gui.view.window.ProgressWindow


/**
 * Runs a gui progress task.
 */
fun runProgressTask(status: String = "Please Wait...", task: suspend (Progress) -> Unit) = runBlocking {
    val progressWindow = ProgressWindow()
    progressWindow.openModal()

    val progress = object : Progress {
        override var currentProgress = 0.0
        override var statusText: String = status
        override var onComplete: () -> Unit = {}
        override fun setProgress(progress: Double) {
            currentProgress = progress
            progressWindow.updateProgress(currentProgress)
            progressWindow.updateStatus(statusText)
        }
        override fun complete() {
            Platform.runLater { progressWindow.close() }
            this.onComplete()
        }
    }

    runAsyncTask(task, progress)
}

/**
 * Executes the suspending task and updates the progress.
 */
private suspend fun runAsyncTask(task: suspend (Progress) -> Unit, progress: Progress) {
    GlobalScope.launch {
        async { task(progress) }.await()
        progress.complete()
    }
}