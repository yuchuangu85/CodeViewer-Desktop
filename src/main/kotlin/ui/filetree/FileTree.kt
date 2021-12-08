package ui.filetree

import common.File
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import ui.editor.Editors

// 可展开文件（文件夹）
class ExpandableFile(
    val file: File,
    val level: Int,
) {
    var children: List<ExpandableFile> by mutableStateOf(emptyList())
    val canExpand: Boolean get() = file.hasChildren

    fun toggleExpanded() {
        children = if (children.isEmpty()) {
            file.children
                .map { ExpandableFile(it, level + 1) }
                .sortedWith(compareBy({ it.file.isDirectory }, { it.file.name }))
                .sortedBy { !it.file.isDirectory }
        } else {
            emptyList()
        }
    }
}

// 文件树
class FileTree(root: File, private val editors: Editors) {
    private val expandableRoot = ExpandableFile(root, 0).apply {
        toggleExpanded()
    }

    val items: List<Item> get() = expandableRoot.toItems()

    // 文件树每个子树
    inner class Item constructor(
        private val expandableFile: ExpandableFile
    ) {
        val name: String get() = expandableFile.file.name

        val level: Int get() = expandableFile.level

        val type: ItemType
            get() = if (expandableFile.file.isDirectory) {
                ItemType.Folder(isExpanded = expandableFile.children.isNotEmpty(), canExpand = expandableFile.canExpand)
            } else {
                ItemType.File(ext = expandableFile.file.name.substringAfterLast(".").lowercase())
            }

        fun open() = when (type) {
            is ItemType.Folder -> expandableFile.toggleExpanded()
            is ItemType.File -> editors.open(expandableFile.file)
        }
    }

    sealed class ItemType {
        class Folder(val isExpanded: Boolean, val canExpand: Boolean) : ItemType()
        class File(val ext: String) : ItemType()
    }

    private fun ExpandableFile.toItems(): List<Item> {
        fun ExpandableFile.addTo(list: MutableList<Item>) {
            list.add(Item(this))
            for (child in children) {
                child.addTo(list)
            }
        }

        val list = mutableListOf<Item>()
        addTo(list)
        return list
    }
}
