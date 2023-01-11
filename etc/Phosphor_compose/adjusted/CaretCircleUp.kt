package com.machiav3lli.backup.ui.compose.icons.phosphor


import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType.Companion.NonZero
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap.Companion.Butt
import androidx.compose.ui.graphics.StrokeJoin.Companion.Miter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.ImageVector.Builder
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp
import com.machiav3lli.backup.ui.compose.icons.Phosphor

val Phosphor.CaretCircleUp: ImageVector
    get() {
        if (_caret_circle_up != null) {
            return _caret_circle_up!!
        }
        _caret_circle_up = Builder(
            name = "Caret-circle-up",
            defaultWidth = 24.0.dp,
            defaultHeight = 24.0.dp,
            viewportWidth = 256.0f,
            viewportHeight = 256.0f,
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)), stroke = null, strokeLineWidth = 0.0f,
                strokeLineCap = Butt, strokeLineJoin = Miter, strokeLineMiter = 4.0f,
                pathFillType = NonZero
            ) {
                moveTo(201.5f, 54.5f)
                arcToRelative(103.9f, 103.9f, 0.0f, false, false, -147.0f, 147.0f)
                arcToRelative(103.9f, 103.9f, 0.0f, false, false, 147.0f, -147.0f)
                close()
                moveTo(190.2f, 190.2f)
                arcToRelative(88.0f, 88.0f, 0.0f, true, true, 0.0f, -124.4f)
                arcTo(87.5f, 87.5f, 0.0f, false, true, 190.2f, 190.2f)
                close()
                moveTo(169.9f, 138.6f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, -0.5f, 11.3f)
                arcTo(8.2f, 8.2f, 0.0f, false, true, 164.0f, 152.0f)
                arcToRelative(7.9f, 7.9f, 0.0f, false, true, -5.9f, -2.6f)
                lineTo(128.0f, 116.0f)
                lineTo(97.9f, 149.4f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, -11.8f, -10.8f)
                lineToRelative(36.0f, -40.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 11.8f, 0.0f)
                close()
            }
        }
            .build()
        return _caret_circle_up!!
    }

private var _caret_circle_up: ImageVector? = null



@Preview
@Composable
fun CaretCircleUpPreview() {
    Image(
        Phosphor.CaretCircleUp,
        null
    )
}
