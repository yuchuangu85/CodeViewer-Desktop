package ui.theme

import fife.ui.rsyntaxtextarea.RSyntaxTextArea
import fife.ui.rsyntaxtextarea.Theme
import java.awt.event.ActionEvent
import java.io.IOException
import javax.swing.AbstractAction

/**
 * change the theme
 * Created by yuchuan
 * DATE 2022/1/15
 * TIME 23:34
 */
class ThemeAction internal constructor(name: String?, xml: String, textArea: RSyntaxTextArea) : AbstractAction() {

    private val xml: String
    private val textArea: RSyntaxTextArea

    init {
        putValue(NAME, name)
        this.xml = xml
        this.textArea = textArea
    }

    override fun actionPerformed(e: ActionEvent) {
        val `in` = javaClass.getResourceAsStream("/org/fife/ui/rsyntaxtextarea/themes/$xml")
        try {
            val theme: Theme = Theme.load(`in`)
            theme.apply(textArea)
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }
    }
}