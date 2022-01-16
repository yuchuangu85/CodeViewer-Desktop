import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.MenuScope
import androidx.compose.ui.window.Tray
import common.LocalAppResources
import kotlinx.coroutines.launch
import launcher.launcherWindow
import settings.ThemeState
import window.codeViewerWindow

@Composable
fun ApplicationScope.codeViewerApplication(
    state: CodeViewerApplicationState,
    themeState: ThemeState
) {
    if (themeState.settings.isTrayEnabled && state.windows.isNotEmpty()) {
        applicationTray(state)
    }

    println("main::rememberApplicationState")
    // Code window will bottom
    // if the windows list is changed, call these code
    for (window in state.windows) {
        key(window) {
            codeViewerWindow(window, themeState)
        }
    }

    // New window will top
    // if the launcherWindow list is changed, call these code
    for (window in state.launcherWindow) {
        key(window) {
            launcherWindow(window)
        }
    }
}

@Composable
private fun ApplicationScope.applicationTray(state: CodeViewerApplicationState) {
    Tray(
        LocalAppResources.current.icon,
        state = state.tray,
        tooltip = "CodeViewer",
        menu = { applicationMenu(state) }
    )
}

@Composable
private fun MenuScope.applicationMenu(state: CodeViewerApplicationState) {
    val scope = rememberCoroutineScope()
    fun exit() = scope.launch { state.exit() }

    Item("New", onClick = {
//        state.newWindow(null)
    })
    Separator()
    Item("Exit", onClick = { exit() })
}