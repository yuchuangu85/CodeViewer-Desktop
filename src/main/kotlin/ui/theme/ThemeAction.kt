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
class ThemeAction internal constructor(name: String?, xml: String) : AbstractAction() {

    private var xml: String

    init {
        putValue(NAME, name)
        this.xml = xml
    }

    fun updateTheme(name: String?, xml: String) {
        putValue(NAME, name)
        this.xml = xml
    }

    override fun actionPerformed(e: ActionEvent?) {
//        val inputStream = javaClass.getResourceAsStream("/fife/ui/rsyntaxtextarea/themes/$xml")
//        try {
//            val theme: Theme = Theme.load(inputStream)
//            theme.apply(textArea)
//        } catch (ioe: IOException) {
//            ioe.printStackTrace()
//        }
    }

    fun performTheme(textArea: RSyntaxTextArea) {
        val inputStream = javaClass.getResourceAsStream("/fife/ui/rsyntaxtextarea/themes/$xml")
        try {
            val theme: Theme = Theme.load(inputStream)
            theme.apply(textArea)
        } catch (ioe: IOException) {
            ioe.printStackTrace()
        }
    }
}