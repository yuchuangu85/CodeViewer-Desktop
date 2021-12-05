package window

import CodeViewerApplicationState
import androidx.compose.foundation.text.selection.DisableSelection
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.window.Notification
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import common.AppTheme
import common.Settings
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import ui.CodeViewer
import ui.CodeViewerView
import ui.editor.Editors
import util.AlertDialogResult
import java.io.File
import java.nio.file.Path

@Composable
fun mainView(selectFile: File?) {
    val codeViewer = remember {
        val editors = Editors()
//        val homeFolder: File = java.io.File(System.getProperty("user.home")).toProjectFile()
        CodeViewer(
            editors = editors,
//            fileTree = FileTree(homeFolder, editors),
            fileTree = selectFile,
            settings = Settings()
        )
    }

    DisableSelection {
        MaterialTheme(
            colors = AppTheme.colors.material
        ) {
            Surface {
                CodeViewerView(codeViewer)
            }
        }
    }
}

class CodeViewerWindowState(
    private val application: CodeViewerApplicationState,
    path: Path?,
    file: File?,
    private val exit: (CodeViewerWindowState) -> Unit
) {
    val settings: Settings get() = application.settings

    val window = WindowState()

    var path by mutableStateOf(path)
        private set

    var isChanged by mutableStateOf(false)
        private set

    val openDialog = DialogState<Path?>()
    val saveDialog = DialogState<Path?>()
    val exitDialog = DialogState<AlertDialogResult>()
    val openFolderDialog = DialogState<File?>()

    private var _notifications = Channel<NotepadWindowNotification>(0)
    val notifications: Flow<NotepadWindowNotification> get() = _notifications.receiveAsFlow()

    private var _text by mutableStateOf("")

    var text: String
        get() = _text
        set(value) {
            check(isInit)
            _text = value
            isChanged = true
        }

    var isInit by mutableStateOf(false)
        private set

    fun toggleFullscreen() {
        window.placement = if (window.placement == WindowPlacement.Fullscreen) {
            WindowPlacement.Floating
        } else {
            WindowPlacement.Fullscreen
        }
    }

    suspend fun run() {
        if (path != null) {
            open(path!!)
        } else {
            initNew()
        }
    }

    private suspend fun open(path: Path) {
        isInit = false
        isChanged = false
        this.path = path
        try {
            _text = path.readTextAsync()
            isInit = true
        } catch (e: Exception) {
            e.printStackTrace()
            text = "Cannot read $path"
        }
    }

    private fun initNew() {
        _text = ""
        isInit = true
        isChanged = false
    }

    fun newWindow() {
        application.newWindow()
    }

    suspend fun open() {
        if (askToSave()) {
            val path = openDialog.awaitResult()
            if (path != null) {
                open(path)
            }
        }
    }

    suspend fun openFolder() {
        isInit = false
        isChanged = false;
    }

    suspend fun save(): Boolean {
        check(isInit)
        if (path == null) {
            val path = saveDialog.awaitResult()
            if (path != null) {
                save(path)
                return true
            }
        } else {
            save(path!!)
            return true
        }
        return false
    }

    private var saveJob: Job? = null

    private suspend fun save(path: Path) {
        isChanged = false
        this.path = path

        saveJob?.cancel()
        saveJob = path.launchSaving(text)

        try {
            saveJob?.join()
            _notifications.trySend(NotepadWindowNotification.SaveSuccess(path))
        } catch (e: Exception) {
            isChanged = true
            e.printStackTrace()
            _notifications.trySend(NotepadWindowNotification.SaveError(path))
        }
    }

    suspend fun exit(): Boolean {
        return if (askToSave()) {
            exit(this)
            true
        } else {
            false
        }
    }

    private suspend fun askToSave(): Boolean {
        if (isChanged) {
            when (exitDialog.awaitResult()) {
                AlertDialogResult.Yes -> {
                    if (save()) {
                        return true
                    }
                }
                AlertDialogResult.No -> {
                    return true
                }
                AlertDialogResult.Cancel -> return false
            }
        } else {
            return true
        }

        return false
    }

    fun sendNotification(notification: Notification) {
        application.sendNotification(notification)
    }
}

@OptIn(DelicateCoroutinesApi::class)
private fun Path.launchSaving(text: String) = GlobalScope.launch {
    writeTextAsync(text)
}

private suspend fun Path.writeTextAsync(text: String) = withContext(Dispatchers.IO) {
    toFile().writeText(text)
}

private suspend fun Path.readTextAsync() = withContext(Dispatchers.IO) {
    toFile().readText()
}

sealed class NotepadWindowNotification {
    class SaveSuccess(val path: Path) : NotepadWindowNotification()
    class SaveError(val path: Path) : NotepadWindowNotification()
}

class DialogState<T> {
    private var onResult: CompletableDeferred<T>? by mutableStateOf(null)

    val isAwaiting get() = onResult != null

    suspend fun awaitResult(): T {
        onResult = CompletableDeferred()
        val result = onResult!!.await()
        onResult = null
        return result
    }

    fun onResult(result: T) = onResult!!.complete(result)
}