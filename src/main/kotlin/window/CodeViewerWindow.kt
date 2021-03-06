package window

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.*
import common.LocalAppResources
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ui.CodeViewerMainView
import ui.menu.WindowMenuBar
import settings.ThemeState
import util.fileChooserDialog
import util.yesNoCancelDialog

@Composable
fun codeViewerWindow(
    state: CodeViewerWindowState,
    themeState: ThemeState
) {

    val scope = rememberCoroutineScope()
    fun exit() = scope.launch {
        state.exit()
    }

    Window(
        state = state.window,
        title = titleOf(state),
        icon = LocalAppResources.current.icon,
        onCloseRequest = { exit() }
    ) {
//        LaunchedEffect(Unit) { state.run() }

        WindowNotifications(state)
        WindowMenuBar(state, themeState)
        CodeViewerMainView(state, themeState)

        if (state.openDialog.isAwaiting) {
            fileChooserDialog(
                title = "CodeViewer",
                onResult = {
                    state.openDialog.onResult(it)
                }
            )
        }

        if (state.saveDialog.isAwaiting) {
            fileChooserDialog(
                title = "CodeViewer",
                onResult = { state.saveDialog.onResult(it) }
            )
        }

        if (state.exitDialog.isAwaiting) {
            yesNoCancelDialog(
                title = "CodeViewer",
                message = "Save changes?",
                onResult = { state.exitDialog.onResult(it) }
            )
        }
    }
}

private fun titleOf(state: CodeViewerWindowState): String {
    val changeMark = if (state.isChanged) "*" else ""
    val filePath = state.file
    return "$changeMark$filePath - Code Viewer"
}

@Composable
private fun WindowNotifications(state: CodeViewerWindowState) {
    // Usually we take into account something like LocalLocale.current here
    fun NotepadWindowNotification.format() = when (this) {
        is NotepadWindowNotification.SaveSuccess -> Notification(
            "File is saved", file.path, Notification.Type.Info
        )
        is NotepadWindowNotification.SaveError -> Notification(
            "File isn't saved", file.path, Notification.Type.Error
        )
    }

    LaunchedEffect(Unit) {
        state.notifications.collect {
            state.sendNotification(it.format())
        }
    }
}
