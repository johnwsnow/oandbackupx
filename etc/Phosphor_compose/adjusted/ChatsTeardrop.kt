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

val Phosphor.ChatsTeardrop: ImageVector
    get() {
        if (_chats_teardrop != null) {
            return _chats_teardrop!!
        }
        _chats_teardrop = Builder(
            name = "Chats-teardrop",
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
                moveTo(169.6f, 72.6f)
                arcTo(80.0f, 80.0f, 0.0f, false, false, 16.0f, 104.0f)
                verticalLineToRelative(66.0f)
                arcToRelative(14.0f, 14.0f, 0.0f, false, false, 14.0f, 14.0f)
                horizontalLineTo(86.7f)
                arcTo(80.2f, 80.2f, 0.0f, false, false, 160.0f, 232.0f)
                horizontalLineToRelative(66.0f)
                arcToRelative(14.0f, 14.0f, 0.0f, false, false, 14.0f, -14.0f)
                verticalLineTo(152.0f)
                arcTo(79.8f, 79.8f, 0.0f, false, false, 169.6f, 72.6f)
                close()
                moveTo(32.0f, 104.0f)
                arcToRelative(64.0f, 64.0f, 0.0f, true, true, 64.0f, 64.0f)
                horizontalLineTo(32.0f)
                close()
                moveTo(224.0f, 216.0f)
                horizontalLineTo(160.0f)
                arcToRelative(64.2f, 64.2f, 0.0f, false, true, -55.7f, -32.4f)
                arcTo(80.2f, 80.2f, 0.0f, false, false, 176.0f, 104.0f)
                arcToRelative(83.6f, 83.6f, 0.0f, false, false, -1.3f, -14.3f)
                arcTo(64.0f, 64.0f, 0.0f, false, true, 224.0f, 152.0f)
                close()
            }
        }
            .build()
        return _chats_teardrop!!
    }

private var _chats_teardrop: ImageVector? = null



@Preview
@Composable
fun ChatsTeardropPreview() {
    Image(
        Phosphor.ChatsTeardrop,
        null
    )
}
