package ui.theme

import androidx.compose.runtime.mutableStateOf
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
//        _settings.value.themeAction.updateTheme(name, themeXml)
        _settings.value = CodeViewerSettings()
        _settings.value.themeAction = ThemeAction(name, themeXml)
    }

}