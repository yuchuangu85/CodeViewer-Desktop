package launcher

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageShader
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import common.LocalAppResources
import kotlinx.coroutines.launch
import util.FileChooserDialog
import util.FileDialog

/**
 * Created by yuchuan.gu
 * DATE 2021/12/8
 * TIME 11:53
 */
@Composable
fun LauncherWindow(
    state: LauncherWindowState
) {
    val scope = rememberCoroutineScope()

    fun chooseFile() = scope.launch { state.chooseFile() }
    fun exit() = scope.launch { state.exit() }

    Window(
        state = state.window,
        title = "Welcome to DevStudio",
        icon = LocalAppResources.current.icon,
        onCloseRequest = { exit() }
    ) {
        LaunchedEffect(Unit) { state.run() }
        Row(
            modifier = Modifier.fillMaxSize()
        ) {
            LauncherList()
            LauncherOperatorList(
                chooseFile = {
                    chooseFile()
                }
            )
        }

        if (state.openDialog.isAwaiting) {
            FileChooserDialog(
                title = "Welcome to CodeViewer",
                onResult = {
                    state.openDialog.onResult(it)
                }
            )
        }
    }
}

// 左侧：显示已经导入过的项目列表
@Composable
fun LauncherList() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(0.4f)
            .fillMaxHeight()
            .background(Color(80, 80, 80), RoundedCornerShape(0)),
    ) {

    }
}

// 右侧：导入项目等操作
@OptIn(ExperimentalUnitApi::class)
@Composable
fun LauncherOperatorList(chooseFile: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color(100, 100, 100), RoundedCornerShape(0)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(40.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
            ) {
                Image(
                    painter = BitmapPainter(useResource("ic_launcher.png", ::loadImageBitmap)),
                    contentDescription = "",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(100.dp, 100.dp)
                        .alpha(1f)

                )
            }
            Box(
                modifier = Modifier.align(Alignment.Start)
            ){
                Text(
                    text = "Open File",
                    modifier = Modifier
                        .height(50.dp)
                        .fillMaxWidth()
                        .padding(10.dp, 10.dp, 10.dp, 10.dp)
                        .clickable(onClick = {
                            chooseFile()
                        })
                        .background(Color(108, 108, 108)),
                    color = Color.White,
                    fontSize = TextUnit(20f, TextUnitType.Sp),
                    textAlign = TextAlign.Justify,
                    maxLines = 1,
                    softWrap = true,
                    style = TextStyle(
                        textAlign = TextAlign.Justify,
                    )
                )
            }
        }
    }
}