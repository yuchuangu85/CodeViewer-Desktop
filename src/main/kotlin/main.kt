import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.key
import androidx.compose.ui.window.application
import common.LocalAppResources
import common.rememberAppResources
import window.codeViewerWindow

fun main() = application {
    CompositionLocalProvider(LocalAppResources provides rememberAppResources()) {
        codeViewerApplication(rememberApplicationState())
    }
}

@Composable
fun codeViewerApplication(state: CodeViewerApplicationState) {
    for (window in state.windows) {
        key(window) {
            codeViewerWindow(window)
        }
    }
}