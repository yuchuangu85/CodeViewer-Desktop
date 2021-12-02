package ui.dialog

import java.awt.Dimension
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.File
import javax.swing.JButton
import javax.swing.JFileChooser
import javax.swing.JPanel


/**
 * Created by yuchuan
 * DATE 2021/12/1
 * TIME 23:50
 */
class DirectoryDialog(
    var choosertitle: String
) : JPanel(), ActionListener {

    override fun actionPerformed(e: ActionEvent?) {
        var result: Int
        val go = JButton("Do it")
        go.addActionListener(this)
        add(go)
        val chooser = JFileChooser()
        chooser.currentDirectory = File(".")
        chooser.dialogTitle = choosertitle
        chooser.fileSelectionMode = JFileChooser.DIRECTORIES_ONLY
        // disable the "All files" option.
        chooser.isAcceptAllFileFilterUsed = false
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            println("getCurrentDirectory(): " + chooser.currentDirectory)
            println(("getSelectedFile() : " + chooser.selectedFile))
        } else {
            println("No Selection ")
        }
    }

    override fun getPreferredSize(): Dimension? {
        return Dimension(200, 200)
    }

}