import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.window.application
import com.formdev.flatlaf.FlatDarculaLaf
import com.formdev.flatlaf.FlatLaf
import com.formdev.flatlaf.util.SystemInfo
import common.LocalAppResources
import common.rememberAppResources

fun main() = application {
    setupTheme()
    CompositionLocalProvider(LocalAppResources provides rememberAppResources()) {
        val state = rememberApplicationState()
        codeViewerApplication(state)
    }
}

fun setupTheme() {
    // macOS
    if (SystemInfo.isMacOS) {
        // enable screen menu bar
        // (moves menu bar from JFrame window to top of screen)
        System.setProperty("apple.laf.useScreenMenuBar", "true")

        // application name used in screen menu bar
        // (in first menu after the "apple" menu)
        System.setProperty("apple.awt.application.name", "FlatLaf Demo")

        // appearance of window title bars
        // possible values:
        //   - "system": use current macOS appearance (light or dark)
        //   - "NSAppearanceNameAqua": use light appearance
        //   - "NSAppearanceNameDarkAqua": use dark appearance
        System.setProperty("apple.awt.application.appearance", "system")
    }
    // set theme like Intellij
    FlatLaf.setup(FlatDarculaLaf())

}