package ui.menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.WindowPlacement
import kotlinx.coroutines.launch
import window.CodeViewerWindowState

/**
 * Created by yuchuan
 * DATE 2021/12/3
 * TIME 22:27
 */
@Composable
fun FrameWindowScope.windowMenuBar(state: CodeViewerWindowState) = MenuBar {
    val scope = rememberCoroutineScope()

    fun save() = scope.launch { state.save() }
    fun open() = scope.launch { state.open() }
    fun openFolder() = scope.launch { state.openFolder() }
    fun exit() = scope.launch { state.exit() }

    Menu("File") {
        Item("New window", onClick = state::newWindow)
        Item("Open...", onClick = { open() })
        Item("Open Folder", onClick = { openFolder() })
        Item("Save", onClick = { save() }, enabled = state.isChanged || state.path == null)
        Separator()
        Item("Exit", onClick = { exit() })
    }

    Menu("Settings") {
        Item(
            if (state.settings.isTrayEnabled) "Hide tray" else "Show tray",
            onClick = state.settings::toggleTray
        )
        Item(
            if (state.window.placement == WindowPlacement.Fullscreen) "Exit fullscreen" else "Enter fullscreen",
            onClick = state::toggleFullscreen
        )
    }
}