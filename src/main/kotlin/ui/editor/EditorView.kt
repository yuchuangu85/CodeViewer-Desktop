package ui.editor

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import common.AppTheme
import settings.CodeViewerSettings
import kotlin.text.Regex.Companion.fromLiteral

@Composable
fun EditorView(
    model: Editor,
    settings: CodeViewerSettings
) = key(model) {
    with (LocalDensity.current) {
        SelectionContainer {
            Surface(
                Modifier.fillMaxSize(),
                color = AppTheme.colors.backgroundDark,
            ) {


//                if (lines != null) {
//                    Box {
//                        Lines(lines!!, settings)
//                        Box(
//                            Modifier
//                                .offset(
//                                    x = settings.fontSize.toDp() * 0.5f * settings.maxLineSymbols
//                                )
//                                .width(1.dp)
//                                .fillMaxHeight()
//                                .background(AppTheme.colors.backgroundLight)
//                        )
//                    }
//                } else {
//                    CircularProgressIndicator(
//                        modifier = Modifier
//                            .size(36.dp)
//                            .padding(4.dp)
//                    )
//                }
            }
        }
    }
}

private fun AnnotatedString.Builder.addStyle(style: SpanStyle, text: String, regexp: String) {
    addStyle(style, text, fromLiteral(regexp))
}

private fun AnnotatedString.Builder.addStyle(style: SpanStyle, text: String, regexp: Regex) {
    for (result in regexp.findAll(text)) {
        addStyle(style, result.range.first, result.range.last + 1)
    }
}