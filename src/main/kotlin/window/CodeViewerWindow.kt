package window

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.*
import common.LocalAppResources
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ui.menu.windowMenuBar
import util.fileChooserDialog
import util.fileDialog
import util.yesNoCancelDialog

@Composable
fun codeViewerWindow(state: CodeViewerWindowState) {

    val scope = rememberCoroutineScope()
    var selectFile: java.io.File? = null

    fun exit() = scope.launch { state.exit() }

    Window(
        state = state.window,
        title = titleOf(state),
        icon = LocalAppResources.current.icon,
        onCloseRequest = { exit() }
    ) {
        LaunchedEffect(Unit) { state.run() }

        windowNotifications(state)
        windowMenuBar(state)

        mainView(selectFile)

        if (state.openDialog.isAwaiting) {
            fileDialog(
                title = "CodeViewer",
                isLoad = true,
                onResult = {
                    state.openDialog.onResult(it)
                }
            )
        }

        if (state.saveDialog.isAwaiting) {
            fileDialog(
                title = "CodeViewer",
                isLoad = false,
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

        if (state.openFolderDialog.isAwaiting) {
            fileChooserDialog(
                title = "File Chooser",
            ) { state.openFolderDialog.onResult(it) }
        }
    }
}

private fun titleOf(state: CodeViewerWindowState): String {
    val changeMark = if (state.isChanged) "*" else ""
    val filePath = state.path ?: "Untitled"
    return "$changeMark$filePath - Notepad"
}

@Composable
private fun windowNotifications(state: CodeViewerWindowState) {
    // Usually we take into account something like LocalLocale.current here
    fun NotepadWindowNotification.format() = when (this) {
        is NotepadWindowNotification.SaveSuccess -> Notification(
            "File is saved", path.toString(), Notification.Type.Info
        )
        is NotepadWindowNotification.SaveError -> Notification(
            "File isn't saved", path.toString(), Notification.Type.Error
        )
    }

    LaunchedEffect(Unit) {
        state.notifications.collect {
            state.sendNotification(it.format())
        }
    }
}
