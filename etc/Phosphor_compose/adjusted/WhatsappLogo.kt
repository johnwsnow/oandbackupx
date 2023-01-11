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

val Phosphor.WhatsappLogo: ImageVector
    get() {
        if (_whatsapp_logo != null) {
            return _whatsapp_logo!!
        }
        _whatsapp_logo = Builder(
            name = "Whatsapp-logo",
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
                moveTo(128.0f, 24.0f)
                arcTo(104.0f, 104.0f, 0.0f, false, false, 36.8f, 178.0f)
                lineToRelative(-8.5f, 30.0f)
                arcTo(15.9f, 15.9f, 0.0f, false, false, 48.0f, 227.7f)
                lineToRelative(30.0f, -8.5f)
                arcTo(104.0f, 104.0f, 0.0f, true, false, 128.0f, 24.0f)
                close()
                moveTo(128.0f, 216.0f)
                arcToRelative(88.4f, 88.4f, 0.0f, false, true, -44.9f, -12.3f)
                arcToRelative(8.7f, 8.7f, 0.0f, false, false, -4.1f, -1.1f)
                arcToRelative(8.3f, 8.3f, 0.0f, false, false, -2.2f, 0.3f)
                lineToRelative(-33.2f, 9.5f)
                lineToRelative(9.5f, -33.2f)
                arcToRelative(8.2f, 8.2f, 0.0f, false, false, -0.8f, -6.3f)
                arcTo(88.0f, 88.0f, 0.0f, true, true, 128.0f, 216.0f)
                close()
                moveTo(180.5f, 143.1f)
                lineTo(160.0f, 131.4f)
                arcToRelative(15.8f, 15.8f, 0.0f, false, false, -16.1f, 0.2f)
                lineTo(132.0f, 138.7f)
                arcTo(41.4f, 41.4f, 0.0f, false, true, 117.3f, 124.0f)
                lineToRelative(7.1f, -11.9f)
                arcToRelative(15.8f, 15.8f, 0.0f, false, false, 0.2f, -16.1f)
                lineTo(112.9f, 75.5f)
                arcTo(14.9f, 14.9f, 0.0f, false, false, 100.0f, 68.0f)
                arcToRelative(36.0f, 36.0f, 0.0f, false, false, -36.0f, 35.9f)
                arcTo(88.1f, 88.1f, 0.0f, false, false, 152.0f, 192.0f)
                horizontalLineToRelative(0.1f)
                arcTo(36.0f, 36.0f, 0.0f, false, false, 188.0f, 156.0f)
                arcTo(14.9f, 14.9f, 0.0f, false, false, 180.5f, 143.1f)
                close()
                moveTo(152.1f, 176.0f)
                arcTo(72.0f, 72.0f, 0.0f, false, true, 80.0f, 103.9f)
                arcTo(19.9f, 19.9f, 0.0f, false, true, 99.4f, 84.0f)
                lineToRelative(11.3f, 19.9f)
                lineToRelative(-9.4f, 15.6f)
                arcToRelative(8.1f, 8.1f, 0.0f, false, false, -0.4f, 7.4f)
                arcToRelative(55.4f, 55.4f, 0.0f, false, false, 28.2f, 28.2f)
                arcToRelative(8.1f, 8.1f, 0.0f, false, false, 7.4f, -0.4f)
                lineToRelative(15.6f, -9.4f)
                lineTo(172.0f, 156.6f)
                arcTo(19.9f, 19.9f, 0.0f, false, true, 152.1f, 176.0f)
                close()
            }
        }
            .build()
        return _whatsapp_logo!!
    }

private var _whatsapp_logo: ImageVector? = null



@Preview
@Composable
fun WhatsappLogoPreview() {
    Image(
        Phosphor.WhatsappLogo,
        null
    )
}
