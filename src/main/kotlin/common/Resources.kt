package common

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
fun Font(name: String, res: String, weight: FontWeight, style: FontStyle): Font =
       androidx.compose.ui.text.platform.Font("font/$res.ttf", weight, style)