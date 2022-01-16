package ui.menu

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.MenuBar
import androidx.compose.ui.window.WindowPlacement
import kotlinx.coroutines.launch
import ui.theme.ThemeAction
import ui.theme.ThemeState
import window.CodeViewerWindowState
import javax.swing.ButtonGroup
import javax.swing.JMenu
import javax.swing.JRadioButtonMenuItem

/**
 * Created by yuchuan
 * DATE 2021/12/3
 * TIME 22:27
 */
@Composable
fun FrameWindowScope.WindowMenuBar(state: CodeViewerWindowState, themeState: ThemeState) = MenuBar {
    val scope = rememberCoroutineScope()

    fun open() = scope.launch { state.open() }
    fun exit() = scope.launch { state.exit() }
    fun changeTheme(name: String, themeXml: String) = scope.launch { themeState.changeTheme(name, themeXml) }

    Menu("File") {
//        Item("New window", onClick = state::newWindow)
        Item("Open...", onClick = { open() })
        Separator()
        Item("Exit", onClick = { exit() })
    }

    Menu("Settings") {
        Item(
            if (themeState.settings.isTrayEnabled) "Hide tray" else "Show tray",
            onClick = themeState.settings::toggleTray
        )
        Item(
            if (state.window.placement == WindowPlacement.Fullscreen) "Exit fullscreen" else "Enter fullscreen",
            onClick = state::toggleFullscreen
        )
    }

    Menu("Theme") {
        Item("Default", onClick = { changeTheme("Default", "default.xml") })
        Item("Default (System Selected)", onClick = { changeTheme("Default (System Selection)", "default-alt.xml") })
        Item("Dark", onClick = { changeTheme("Dark", "dark.xml") })
        Item("Druid", onClick = { changeTheme("Druid", "druid.xml") })
        Item("Monokai", onClick = { changeTheme("Monokai", "monokai.xml") })
        Item("Eclipse", onClick = { changeTheme("Eclipse", "eclipse.xml") })
        Item("IDEA", onClick = { changeTheme("IDEA", "idea.xml") })
        Item("Visual Studio", onClick = { changeTheme("Visual Studio", "vs.xml") })
    }
}
