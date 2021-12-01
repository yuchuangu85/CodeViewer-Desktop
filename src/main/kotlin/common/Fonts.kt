package common

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

object Fonts {
    @Composable
    fun jetbrainsMono() = FontFamily(
        Font(
            "JetBrains Mono",
            "font/jetbrainsmono_regular.ttf",
            FontWeight.Normal,
            FontStyle.Normal
        ),
        Font(
            "JetBrains Mono",
            "font/jetbrainsmono_italic.ttf",
            FontWeight.Normal,
            FontStyle.Italic
        ),

        Font(
            "JetBrains Mono",
            "font/jetbrainsmono_bold.ttf",
            FontWeight.Bold,
            FontStyle.Normal
        ),
        Font(
            "JetBrains Mono",
            "font/jetbrainsmono_bold_italic.ttf",
            FontWeight.Bold,
            FontStyle.Italic
        ),

        Font(
            "JetBrains Mono",
            "font/jetbrainsmono_extrabold.ttf",
            FontWeight.ExtraBold,
            FontStyle.Normal
        ),
        Font(
            "JetBrains Mono",
            "font/jetbrainsmono_extrabold_italic.ttf",
            FontWeight.ExtraBold,
            FontStyle.Italic
        ),

        Font(
            "JetBrains Mono",
            "font/jetbrainsmono_medium.ttf",
            FontWeight.Medium,
            FontStyle.Normal
        ),
        Font(
            "JetBrains Mono",
            "font/jetbrainsmono_medium_italic.ttf",
            FontWeight.Medium,
            FontStyle.Italic
        )
    )
}