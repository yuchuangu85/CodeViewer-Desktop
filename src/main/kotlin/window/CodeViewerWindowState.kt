package window

import CodeViewerApplicationState
import androidx.compose.runtime.*
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Notification
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import common.Settings
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import util.AlertDialogResult
import java.io.File


class CodeViewerWindowState(
    private val application: CodeViewerApplicationState,
    file: File,
    private val exit: (CodeViewerWindowState) -> Unit
) {
    val settings: Settings get() = application.settings

    val window = WindowState(
        size = DpSize(1200.dp, 800.dp)
    )

    var isChanged by mutableStateOf(false)
        private set

    // Code File or directory imported
    var file by mutableStateOf(file)
        private set

    val openDialog = DialogState<File?>()
    val saveDialog = DialogState<File?>()
    val exitDialog = DialogState<AlertDialogResult>()

    private var _notifications = Channel<NotepadWindowNotification>(0)
    val notifications: Flow<NotepadWindowNotification> get() = _notifications.receiveAsFlow()

    fun toggleFullscreen() {
        window.placement = if (window.placement == WindowPlacement.Fullscreen) {
            WindowPlacement.Floating
        } else {
            WindowPlacement.Fullscreen
        }
    }

    fun open() {
        application.newLauncherWindow()
    }

    fun exit(): Boolean {
        exit(this)
        return true
    }

    fun changeTheme(name: String, themeXml: String) {

    }

    fun sendNotification(notification: Notification) {
        application.sendNotification(notification)
    }
}

sealed class NotepadWindowNotification {
    class SaveSuccess(val file: File) : NotepadWindowNotification()
    class SaveError(val file: File) : NotepadWindowNotification()
}

class DialogState<T> {
    private var onResult: CompletableDeferred<T>? by mutableStateOf(null)

    val isAwaiting get() = onResult != null

    suspend fun awaitResult(): T {
        onResult = CompletableDeferred()
        val result = onResult!!.await()
        onResult = null
        return result
    }

    fun onResult(result: T) = onResult!!.complete(result)
}