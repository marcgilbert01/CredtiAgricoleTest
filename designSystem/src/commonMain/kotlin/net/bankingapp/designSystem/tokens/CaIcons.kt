package net.bankingapp.designSystem.tokens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

@Composable
fun rememberChevronRight(): ImageVector {
    return remember {
        ImageVector.Builder(
            name = "chevron_right",
            defaultWidth = 40.0.dp,
            defaultHeight = 40.0.dp,
            viewportWidth = 40.0f,
            viewportHeight = 40.0f
        ).apply {
            path(
                fill = SolidColor(Color.Black),
                fillAlpha = 1f,
                stroke = null,
                strokeAlpha = 1f,
                strokeLineWidth = 1.0f,
                strokeLineCap = StrokeCap.Butt,
                strokeLineJoin = StrokeJoin.Miter,
                strokeLineMiter = 1f,
                pathFillType = PathFillType.NonZero
            ) {
                moveTo(14.417f, 29.167f)
                quadToRelative(-0.5f, -0.542f, -0.5f, -1.25f)
                quadToRelative(0f, -0.709f, 0.5f, -1.209f)
                lineToRelative(6.75f, -6.75f)
                lineToRelative(-6.75f, -6.75f)
                quadToRelative(-0.5f, -0.5f, -0.5f, -1.25f)
                reflectiveQuadToRelative(0.5f, -1.25f)
                quadToRelative(0.583f, -0.541f, 1.271f, -0.52f)
                quadToRelative(0.687f, 0.02f, 1.229f, 0.52f)
                lineToRelative(8f, 8.042f)
                quadToRelative(0.25f, 0.25f, 0.395f, 0.562f)
                quadToRelative(0.146f, 0.313f, 0.146f, 0.646f)
                quadToRelative(0f, 0.375f, -0.146f, 0.688f)
                quadToRelative(-0.145f, 0.312f, -0.395f, 0.562f)
                lineToRelative(-7.959f, 8f)
                quadToRelative(-0.541f, 0.5f, -1.25f, 0.5f)
                quadToRelative(-0.708f, 0f, -1.291f, -0.541f)
                close()
            }
        }.build()
    }
}