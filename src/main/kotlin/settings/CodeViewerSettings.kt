package settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.sp
import ui.theme.ThemeAction

class CodeViewerSettings {

    var fontSize by mutableStateOf(13.sp)
    val maxLineSymbols = 120

    var themeAction by mutableStateOf(ThemeAction("Default", "default.xml"))

    var isTrayEnabled by mutableStateOf(true)
        private set

    fun toggleTray() {
        isTrayEnabled = !isTrayEnabled
    }
}