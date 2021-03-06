package util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.window.AwtWindow
import androidx.compose.ui.window.FrameWindowScope
import androidx.compose.ui.window.WindowScope
import javafx.application.Platform
import javafx.embed.swing.JFXPanel
import javafx.scene.Group
import javafx.scene.Scene
import javafx.stage.DirectoryChooser
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.swing.Swing
import java.awt.*
import java.io.File
import java.nio.file.Path
import javax.swing.*
import javax.swing.filechooser.FileSystemView
import javafx.scene.paint.Color as JFXColor


@Composable
fun FrameWindowScope.fileDialog(
    title: String,
    isLoad: Boolean,
    onResult: (result: Path?) -> Unit
) = AwtWindow(
    create = {
        object : FileDialog(window, "Choose a file", if (isLoad) LOAD else SAVE) {
            override fun setVisible(value: Boolean) {
                super.setVisible(value)
                if (value) {
                    if (file != null) {
                        onResult(File(directory).resolve(file).toPath())
                    } else {
                        onResult(null)
                    }
                }
            }
        }.apply {
            this.title = title
        }
    },
    dispose = FileDialog::dispose
)

@Composable
fun fileChooserDialog(
    title: String,
    onResult: (result: File) -> Unit
) {
    val fileChooser = JFileChooser(FileSystemView.getFileSystemView())
    fileChooser.currentDirectory = File(System.getProperty("user.dir"))
    fileChooser.dialogTitle = title
    fileChooser.fileSelectionMode = JFileChooser.FILES_AND_DIRECTORIES
    fileChooser.isAcceptAllFileFilterUsed = true
    fileChooser.selectedFile = null
    fileChooser.currentDirectory = null
    if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
        val file = fileChooser.selectedFile
        println("choose file or folder is: $file")
        onResult(file)
    } else {
        onResult(File(""));
        println("No Selection ")
    }
}

@Composable
fun directoryChooserDialog(
    title: String,
    onResult: (result: File) -> Unit,
    op: File.(other: String) -> Unit // op?????????File?????????????????????
) {
    Platform.runLater {
        val directoryChooser = DirectoryChooser()
        directoryChooser.title = title
        val selectedFile = directoryChooser.showDialog(null)
        if (selectedFile != null) {
            onResult(selectedFile)
            op(selectedFile, ", title")
        } else {
            println("No Selection ")
            onResult(File(""));
            op(File(""), " No Selection")
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
@Composable
fun WindowScope.yesNoCancelDialog(
    title: String,
    message: String,
    onResult: (result: AlertDialogResult) -> Unit
) {
    DisposableEffect(Unit) {
        val job = GlobalScope.launch(Dispatchers.Swing) {
            val resultInt = JOptionPane.showConfirmDialog(
                window, message, title, JOptionPane.YES_NO_CANCEL_OPTION
            )
            val result = when (resultInt) {
                JOptionPane.YES_OPTION -> AlertDialogResult.Yes
                JOptionPane.NO_OPTION -> AlertDialogResult.No
                else -> AlertDialogResult.Cancel
            }
            onResult(result)
        }

        onDispose {
            job.cancel()
        }
    }
}

enum class AlertDialogResult {
    Yes, No, Cancel
}

@Composable
fun javaFXPanel(
    root: Container,
    panel: JFXPanel,
    onCreate: () -> Unit
) {
    val container = remember { JPanel() }
    val density = LocalDensity.current.density

    Layout(
        content = {},
//        modifier = Modifier.onGloballyPositioned { childCoordinates ->
//            val coordinates = childCoordinates.parentCoordinates!!
//            val location = coordinates.localToWindow(Offset.Zero).round()
//            val size = coordinates.size
//            container.setBounds(
//                (location.x / density).toInt(),
//                (location.y / density).toInt(),
//                (size.width / density).toInt(),
//                (size.height / density).toInt()
//            )
//            container.validate()
//            container.repaint()
//        },
        measurePolicy = { _, _ ->
            layout(0, 0) {}
        }
    )

    DisposableEffect(Unit) {
        container.apply {
            layout = BorderLayout(0, 0)
            add(panel)
        }
        root.add(container)
        onCreate.invoke()
        onDispose {
            root.remove(container)
        }
    }
}