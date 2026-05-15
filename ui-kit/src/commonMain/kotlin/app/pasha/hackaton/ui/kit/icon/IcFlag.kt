package app.pasha.hackaton.ui.kit.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathData
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IcFlag: ImageVector
    get() {
        if (_IcFlag != null) {
            return _IcFlag!!
        }
        _IcFlag = ImageVector.Builder(
            name = "IcFlag",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            group(
                clipPathData = PathData {
                    moveTo(2f, 4.018f)
                    lineTo(22f, 4.018f)
                    arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 24f, 6.018f)
                    lineTo(24f, 18.018f)
                    arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 22f, 20.018f)
                    lineTo(2f, 20.018f)
                    arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 18.018f)
                    lineTo(0f, 6.018f)
                    arcTo(2f, 2f, 0f, isMoreThanHalf = false, isPositiveArc = true, 2f, 4.018f)
                    close()
                }
            ) {
            }
            group(
                clipPathData = PathData {
                    moveTo(0f, 4.018f)
                    horizontalLineToRelative(24f)
                    verticalLineToRelative(15.964f)
                    horizontalLineToRelative(-24f)
                    close()
                }
            ) {
                path(fill = SolidColor(Color.White)) {
                    moveTo(0f, 4.018f)
                    horizontalLineToRelative(24f)
                    verticalLineToRelative(15.964f)
                    horizontalLineToRelative(-24f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF509E2F))) {
                    moveTo(0.004f, 4.018f)
                    horizontalLineTo(24.004f)
                    verticalLineTo(19.982f)
                    horizontalLineTo(0.004f)
                    verticalLineTo(4.018f)
                    close()
                }
                path(fill = SolidColor(Color(0xFFEF3340))) {
                    moveTo(0.004f, 4.018f)
                    horizontalLineTo(24.004f)
                    verticalLineTo(14.66f)
                    horizontalLineTo(0.004f)
                    verticalLineTo(4.018f)
                    close()
                }
                path(fill = SolidColor(Color(0xFF00B5E2))) {
                    moveTo(0.004f, 4.018f)
                    horizontalLineTo(24.004f)
                    verticalLineTo(9.339f)
                    horizontalLineTo(0.004f)
                    verticalLineTo(4.018f)
                    close()
                }
                path(fill = SolidColor(Color.White)) {
                    moveTo(11.58f, 14.199f)
                    curveTo(12.772f, 14.199f, 13.739f, 13.233f, 13.739f, 12.04f)
                    curveTo(13.739f, 10.849f, 12.772f, 9.882f, 11.58f, 9.882f)
                    curveTo(10.388f, 9.882f, 9.422f, 10.849f, 9.422f, 12.04f)
                    curveTo(9.422f, 13.233f, 10.388f, 14.199f, 11.58f, 14.199f)
                    close()
                }
                path(fill = SolidColor(Color(0xFFED2939))) {
                    moveTo(12.06f, 13.839f)
                    curveTo(13.054f, 13.839f, 13.859f, 13.034f, 13.859f, 12.04f)
                    curveTo(13.859f, 11.047f, 13.054f, 10.242f, 12.06f, 10.242f)
                    curveTo(11.067f, 10.242f, 10.262f, 11.047f, 10.262f, 12.04f)
                    curveTo(10.262f, 13.034f, 11.067f, 13.839f, 12.06f, 13.839f)
                    close()
                }
                path(fill = SolidColor(Color.White)) {
                    moveTo(13.978f, 10.841f)
                    lineTo(14.209f, 11.486f)
                    lineTo(14.827f, 11.192f)
                    lineTo(14.533f, 11.813f)
                    lineTo(15.177f, 12.04f)
                    lineTo(14.533f, 12.271f)
                    lineTo(14.827f, 12.889f)
                    lineTo(14.209f, 12.595f)
                    lineTo(13.978f, 13.239f)
                    lineTo(13.748f, 12.595f)
                    lineTo(13.13f, 12.889f)
                    lineTo(13.424f, 12.271f)
                    lineTo(12.779f, 12.04f)
                    lineTo(13.424f, 11.809f)
                    lineTo(13.13f, 11.192f)
                    lineTo(13.748f, 11.486f)
                    lineTo(13.978f, 10.841f)
                    close()
                }
            }
        }.build()

        return _IcFlag!!
    }

@Suppress("ObjectPropertyName")
private var _IcFlag: ImageVector? = null
