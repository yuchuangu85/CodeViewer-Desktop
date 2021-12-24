import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Notification
import androidx.compose.ui.window.TrayState
import common.Settings
import launcher.LauncherWindowState
import window.CodeViewerWindowState
import java.io.File

@Composable
fun rememberApplicationState() = remember {
    CodeViewerApplicationState().apply {
        newLauncherWindow()
    }
}

class CodeViewerApplicationState {
    val settings = Settings()
    val tray = TrayState()

    private val _launcherWindows = mutableStateListOf<LauncherWindowState>()
    val launcherWindow: List<LauncherWindowState> get() = _launcherWindows

    fun newLauncherWindow() {
        _launcherWindows.add(
            LauncherWindowState(
                application = this,
                exit = _launcherWindows::remove,
                openEditor = {
                    newWindow(it.file)
                }
            )
        )
    }

    private val _windows = mutableStateListOf<CodeViewerWindowState>()
    val windows: List<CodeViewerWindowState> get() = _windows

    private fun newWindow(file: File) {
        println("newWindow: ${file.path}")
        _windows.add(
            CodeViewerWindowState(
                application = this,
                file = file,
                exit = _windows::remove
            )
        )
    }

    fun sendNotification(notification: Notification) {
        tray.sendNotification(notification)
    }

    fun exit() {
        val windowsCopy = windows.reversed()
        for (window in windowsCopy) {
            if (!window.exit()) {
                break
            }
        }
    }
}