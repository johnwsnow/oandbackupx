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

val Phosphor.NumberFour: ImageVector
    get() {
        if (_number_four != null) {
            return _number_four!!
        }
        _number_four = Builder(
            name = "Number-four",
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
                moveTo(180.0f, 96.0f)
                verticalLineTo(224.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, -16.0f, 0.0f)
                verticalLineTo(168.0f)
                horizontalLineTo(76.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, -6.5f, -3.4f)
                arcToRelative(7.9f, 7.9f, 0.0f, false, true, -1.0f, -7.3f)
                lineToRelative(48.0f, -136.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 15.0f, 5.4f)
                lineTo(87.3f, 152.0f)
                horizontalLineTo(164.0f)
                verticalLineTo(96.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 16.0f, 0.0f)
                close()
            }
        }
            .build()
        return _number_four!!
    }

private var _number_four: ImageVector? = null



@Preview
@Composable
fun NumberFourPreview() {
    Image(
        Phosphor.NumberFour,
        null
    )
}
