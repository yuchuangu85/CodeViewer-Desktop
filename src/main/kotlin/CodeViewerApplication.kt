import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.MenuScope
import androidx.compose.ui.window.Tray
import common.LocalAppResources
import kotlinx.coroutines.launch
import launcher.LauncherWindow
import window.CodeViewerWindow

@Composable
fun ApplicationScope.CodeViewerApplication(state: CodeViewerApplicationState) {
    if (state.settings.isTrayEnabled && state.windows.isNotEmpty()) {
        ApplicationTray(state)
    }

    for (window in state.launcherWindow) {
        key(window) {
            LauncherWindow(window)
        }
    }

    for (window in state.windows) {
        key(window) {
            CodeViewerWindow(window)
        }
    }
}

@Composable
private fun ApplicationScope.ApplicationTray(state: CodeViewerApplicationState) {
    Tray(
        LocalAppResources.current.icon,
        state = state.tray,
        tooltip = "CodeViewer",
        menu = { ApplicationMenu(state) }
    )
}

@Composable
private fun MenuScope.ApplicationMenu(state: CodeViewerApplicationState) {
    val scope = rememberCoroutineScope()
    fun exit() = scope.launch { state.exit() }

    Item("New", onClick = {
//        state.newWindow(null)
    })
    Separator()
    Item("Exit", onClick = { exit() })
}