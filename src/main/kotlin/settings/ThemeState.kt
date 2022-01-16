package settings

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.unit.TextUnit
import settings.CodeViewerSettings

/**
 * Created by yuchuan
 * DATE 2022/1/16
 * TIME 17:29
 */
class ThemeState {

    private val _settings = mutableStateOf(CodeViewerSettings())
    val settings: CodeViewerSettings get() = _settings.value

    fun changeTheme(name: String, themeXml: String) {
        val newSettings = CodeViewerSettings()
        newSettings.fontSize = settings.fontSize
        _settings.value = newSettings
        _settings.value.themeAction.updateTheme(name, themeXml)
    }

    fun resetFontSize(fontSize: TextUnit) {
        val newSettings = CodeViewerSettings()
        newSettings.themeAction = settings.themeAction
        _settings.value = newSettings
        _settings.value.fontSize = fontSize
    }

}