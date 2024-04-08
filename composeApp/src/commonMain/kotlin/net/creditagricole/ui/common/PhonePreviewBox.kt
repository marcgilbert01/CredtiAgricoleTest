package net.creditagricole.ui.common


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PhonePreviewBox(
    component: @Composable () -> Unit
) {
    Box(Modifier.width(400.dp).height(800.dp).background(Color.White)) {
        component()
    }
}