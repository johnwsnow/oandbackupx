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

val Phosphor.BookmarksSimple: ImageVector
    get() {
        if (_bookmarks_simple != null) {
            return _bookmarks_simple!!
        }
        _bookmarks_simple = Builder(
            name = "Bookmarks-simple",
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
                moveTo(160.0f, 56.0f)
                lineTo(64.0f, 56.0f)
                arcTo(16.0f, 16.0f, 0.0f, false, false, 48.0f, 72.0f)
                lineTo(48.0f, 224.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, false, 8.0f, 8.0f)
                arcToRelative(8.2f, 8.2f, 0.0f, false, false, 4.7f, -1.5f)
                lineTo(112.0f, 193.8f)
                lineToRelative(51.4f, 36.7f)
                arcToRelative(7.8f, 7.8f, 0.0f, false, false, 8.3f, 0.6f)
                arcTo(8.1f, 8.1f, 0.0f, false, false, 176.0f, 224.0f)
                lineTo(176.0f, 72.0f)
                arcTo(16.0f, 16.0f, 0.0f, false, false, 160.0f, 56.0f)
                close()
                moveTo(160.0f, 208.5f)
                lineTo(116.6f, 177.5f)
                arcTo(7.7f, 7.7f, 0.0f, false, false, 112.0f, 176.0f)
                arcToRelative(8.2f, 8.2f, 0.0f, false, false, -4.7f, 1.5f)
                lineTo(64.0f, 208.5f)
                lineTo(64.0f, 72.0f)
                horizontalLineToRelative(96.0f)
                close()
                moveTo(208.0f, 40.0f)
                lineTo(208.0f, 192.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, -16.0f, 0.0f)
                lineTo(192.0f, 40.0f)
                lineTo(88.0f, 40.0f)
                arcToRelative(8.0f, 8.0f, 0.0f, false, true, 0.0f, -16.0f)
                lineTo(192.0f, 24.0f)
                arcTo(16.0f, 16.0f, 0.0f, false, true, 208.0f, 40.0f)
                close()
            }
        }
            .build()
        return _bookmarks_simple!!
    }

private var _bookmarks_simple: ImageVector? = null



@Preview
@Composable
fun BookmarksSimplePreview() {
    Image(
        Phosphor.BookmarksSimple,
        null
    )
}
