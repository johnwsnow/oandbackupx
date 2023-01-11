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

val Phosphor.ThermometerHot: ImageVector
    get() {
        if (_thermometer_hot != null) {
            return _thermometer_hot!!
        }
        _thermometer_hot = Builder(
            name = "Thermometer-hot",
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
                moveTo(128.0f, 161.2f)
                lineTo(128.0f, 48.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, -16.0f, 0.0f)
                lineTo(112.0f, 161.2f)
                arcToRelative(28.0f, 28.0f, 0.0f, true, false, 16.0f, 0.0f)
                close()
                moveTo(120.0f, 200.0f)
                arcToRelative(12.0f, 12.0f, 0.0f, true, true, 12.0f, -12.0f)
                arcTo(12.0f, 12.0f, 0.0f, false, true, 120.0f, 200.0f)
                close()
                moveTo(178.1f, 85.7f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 0.0f, -11.4f)
                arcToRelative(28.0f, 28.0f, 0.0f, false, true, 19.8f, -8.2f)
                arcToRelative(28.4f, 28.4f, 0.0f, false, true, 19.8f, 8.2f)
                arcToRelative(11.9f, 11.9f, 0.0f, false, false, 16.9f, 0.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 11.3f, 11.4f)
                arcToRelative(28.0f, 28.0f, 0.0f, false, true, -19.8f, 8.2f)
                arcToRelative(28.4f, 28.4f, 0.0f, false, true, -19.8f, -8.2f)
                arcToRelative(11.9f, 11.9f, 0.0f, false, false, -16.9f, 0.0f)
                arcTo(8.0f, 8.0f, 0.0f, false, true, 178.1f, 85.7f)
                close()
                moveTo(245.9f, 114.3f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 0.0f, 11.4f)
                arcToRelative(28.0f, 28.0f, 0.0f, false, true, -19.8f, 8.2f)
                arcToRelative(28.4f, 28.4f, 0.0f, false, true, -19.8f, -8.2f)
                arcToRelative(11.9f, 11.9f, 0.0f, false, false, -16.9f, 0.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, -11.3f, -11.4f)
                arcToRelative(28.0f, 28.0f, 0.0f, false, true, 19.8f, -8.2f)
                arcToRelative(28.4f, 28.4f, 0.0f, false, true, 19.8f, 8.2f)
                arcToRelative(11.9f, 11.9f, 0.0f, false, false, 16.9f, 0.0f)
                arcTo(8.0f, 8.0f, 0.0f, false, true, 245.9f, 114.3f)
                close()
                moveTo(160.0f, 143.3f)
                lineTo(160.0f, 48.0f)
                arcToRelative(40.0f, 40.0f, 0.0f, false, false, -80.0f, 0.0f)
                verticalLineToRelative(95.3f)
                arcTo(59.4f, 59.4f, 0.0f, false, false, 60.0f, 188.0f)
                arcToRelative(60.0f, 60.0f, 0.0f, false, false, 120.0f, 0.0f)
                arcTo(59.4f, 59.4f, 0.0f, false, false, 160.0f, 143.3f)
                close()
                moveTo(120.0f, 232.0f)
                arcToRelative(44.0f, 44.0f, 0.0f, false, true, -44.0f, -44.0f)
                arcToRelative(43.5f, 43.5f, 0.0f, false, true, 16.6f, -34.4f)
                arcTo(8.1f, 8.1f, 0.0f, false, false, 96.0f, 147.0f)
                lineTo(96.0f, 48.0f)
                arcToRelative(24.0f, 24.0f, 0.0f, false, true, 48.0f, 0.0f)
                verticalLineToRelative(99.0f)
                arcToRelative(8.2f, 8.2f, 0.0f, false, false, 3.3f, 6.5f)
                arcTo(44.0f, 44.0f, 0.0f, false, true, 120.0f, 232.0f)
                close()
            }
        }
            .build()
        return _thermometer_hot!!
    }

private var _thermometer_hot: ImageVector? = null



@Preview
@Composable
fun ThermometerHotPreview() {
    Image(
        Phosphor.ThermometerHot,
        null
    )
}
