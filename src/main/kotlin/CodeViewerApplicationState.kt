import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Notification
import androidx.compose.ui.window.TrayState
import common.Settings
import window.CodeViewerWindowState

@Composable
fun rememberApplicationState() = remember {
    CodeViewerApplicationState().apply {
        newWindow()
    }
}

class CodeViewerApplicationState {
    val settings = Settings()
    private val tray = TrayState()

    private val _windows = mutableStateListOf<CodeViewerWindowState>()
    val windows: List<CodeViewerWindowState> get() = _windows

    fun newWindow() {
        _windows.add(
            CodeViewerWindowState(
                application = this,
                path = null,
                file = null,
                exit = _windows::remove
            )
        )
    }

    fun sendNotification(notification: Notification) {
        tray.sendNotification(notification)
    }

    suspend fun exit() {
        val windowsCopy = windows.reversed()
        for (window in windowsCopy) {
            if (!window.exit()) {
                break
            }
        }
    }
}