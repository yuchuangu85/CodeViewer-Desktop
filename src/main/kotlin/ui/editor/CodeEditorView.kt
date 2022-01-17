package ui.editor

import androidx.compose.runtime.Composable
import settings.CodeViewerSettings
import fife.ui.rsyntaxtextarea.*
import fife.ui.rtextarea.RTextScrollPane
import java.awt.Color
import java.awt.Font
import java.awt.event.InputEvent
import java.awt.event.KeyEvent
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.StandardCharsets
import javax.swing.Action
import javax.swing.JPanel
import javax.swing.KeyStroke
import javax.swing.event.HyperlinkEvent


/**
 * Created by yuchuan
 * DATE 2022/1/15
 * TIME 20:13
 */
@Composable
fun CodeEditorView(
    model: Editor,
    settings: CodeViewerSettings,
): RTextScrollPane {
    val textArea = CreateTextArea(model, settings)
    textArea.syntaxEditingStyle = SyntaxConstants.SYNTAX_STYLE_KOTLIN
    LoadCodeFile(model, textArea)
    return RTextScrollPane(textArea, true)
}

@Composable
fun LoadCodeFile(model: Editor, textArea: RSyntaxTextArea) {
    val r: BufferedReader
    try {
        val inputStream: InputStream = model.file.inputStream();
        r = BufferedReader(
            InputStreamReader(inputStream, StandardCharsets.UTF_8)
        )
        textArea.read(r, null)
        r.close()
        textArea.caretPosition = 0
        textArea.discardAllEdits()
    } catch (re: RuntimeException) {
        throw re // FindBugs
    } catch (e: Exception) { // Never happens
        textArea.text = "Type here to see syntax highlighting"
    }
}

@Composable
fun CreateTextArea(
    model: Editor,
    settings: CodeViewerSettings
): RSyntaxTextArea {
    val textArea = RSyntaxTextArea(25, 70)
    textArea.tabSize = 3
    textArea.caretPosition = 0
    textArea.addHyperlinkListener {
        if (it.eventType == HyperlinkEvent.EventType.ACTIVATED) {
            val url: URL = it.url
//            if (url == null) {
//                UIManager.getLookAndFeel().provideErrorFeedback(null)
//            } else {
//                JOptionPane.showMessageDialog(
//                    this,
//                    "URL clicked:\n$url"
//                )
//            }
        }
    }
    textArea.requestFocusInWindow()
    textArea.markOccurrences = true
    textArea.isCodeFoldingEnabled = true
    textArea.isClearWhitespaceLinesEnabled = false
    textArea.background = Color.white

    val im = textArea.inputMap
    val am = textArea.actionMap
    im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F6, 0), "decreaseFontSize")
    am.put("decreaseFontSize", RSyntaxTextAreaEditorKit.DecreaseFontSizeAction())
    im.put(KeyStroke.getKeyStroke(KeyEvent.VK_F7, 0), "increaseFontSize")
    am.put("increaseFontSize", RSyntaxTextAreaEditorKit.IncreaseFontSizeAction())

    val ctrlShift = InputEvent.CTRL_DOWN_MASK or InputEvent.SHIFT_DOWN_MASK
    im.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, ctrlShift), "copyAsStyledText")
    am.put("copyAsStyledText", RSyntaxTextAreaEditorKit.CopyCutAsStyledTextAction(false))

    // 添加切换字体和调整字体大小
    try {
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_M, ctrlShift), "copyAsStyledTextMonokai")
        am.put("copyAsStyledTextMonokai", createCopyAsStyledTextAction("monokai"))
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, ctrlShift), "copyAsStyledTextEclipse")
        am.put("copyAsStyledTextEclipse", createCopyAsStyledTextAction("eclipse"))
    } catch (ioe: IOException) {
        ioe.printStackTrace()
    }

    // Since this demo allows the LookAndFeel and RSyntaxTextArea Theme to
    // be toggled independently of one another, we set this property to
    // true so matched bracket popups look good.  In an app where the
    // developer ensures the RSTA Theme always matches the LookAndFeel as
    // far as light/dark is concerned, this property can be omitted.

    // Since this demo allows the LookAndFeel and RSyntaxTextArea Theme to
    // be toggled independently of one another, we set this property to
    // true so matched bracket popups look good.  In an app where the
    // developer ensures the RSTA Theme always matches the LookAndFeel as
    // far as light/dark is concerned, this property can be omitted.
    System.setProperty(MatchedBracketPopup.PROPERTY_CONSIDER_TEXTAREA_BACKGROUND, "true")
    settings.themeAction.performTheme(textArea)
    textArea.font = Font("Default", Font.ITALIC, settings.fontSize.value.toInt())
    return textArea
}

@Throws(IOException::class)
private fun createCopyAsStyledTextAction(themeName: String): Action {
    val resource = "/fife/ui/rsyntaxtextarea/themes/$themeName.xml"
    val theme: Theme =
        Theme.load(RSyntaxTextArea::class.java.getResourceAsStream(resource))
    return RSyntaxTextAreaEditorKit.CopyCutAsStyledTextAction(themeName, theme, false)
}