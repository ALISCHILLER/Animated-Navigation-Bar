package com.zar.bottomnavghtioncompose.component.botttomnava.shape

import android.graphics.PointF
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path

class IndentPath(
    private val rect: Rect,
    private val radius: Float = 10f // شعاع دایره
) {
    private val maxX = 110f
    private val maxY = 34f

    private fun translate(x: Float, y: Float): PointF {
        return PointF(
            ((x / maxX) * rect.width) + rect.left,
            ((y / maxY) * rect.height) + rect.top
        )
    }

    fun createPath(): Path {
        val start = translate(x = 0f, y = 0f)
        val middle = translate(x = 55f, y = 34f)
        val end = translate(x = 110f, y = 0f)

        val control1 = translate(x = 23f, y = 0f)
        val control2 = translate(x = 39f, y = 34f)
        val control3 = translate(x = 71f, y = 34f)
        val control4 = translate(x = 87f, y = 0f)

        val path = Path()
        path.moveTo(start.x, start.y)
        path.cubicTo(control1.x, control1.y, control2.x, control2.y, middle.x, middle.y)
        path.cubicTo(control3.x, control3.y, control4.x, control4.y, end.x, end.y)

        return path
    }
    fun createPathCircab(): Path {
        val start = translate(x = 0f, y = 0f)
        val middle = translate(x = 55f, y = 34f)
        val end = translate(x = 110f, y = 0f)

        val control1 = translate(x = 23f, y = 0f)
        val control2 = translate(x = 39f, y = 34f)
        val control3 = translate(x = 71f, y = 34f)
        val control4 = translate(x = 87f, y = 0f)

        val path = Path()
        path.moveTo(start.x, start.y)
        path.cubicTo(control1.x, control1.y, control2.x, control2.y, middle.x, middle.y)
        path.cubicTo(control3.x, control3.y, control4.x, control4.y, end.x, end.y)

        // افزودن یک دایره به ته مسیر
        path.addOval(Rect(end.x - radius, end.y - radius, end.x + radius, end.y + radius))

        return path
    }
    fun createCircalPath(): Path {
        val start = translate(x = 0f, y = 0f)
        val middle = translate(x = 55f, y = 34f)
        val end = translate(x = 110f, y = 0f)

        val control1 = translate(x = 23f, y = 0f)
        val control2 = translate(x = 39f, y = 34f)
        val control3 = translate(x = 71f, y = 34f)
        val control4 = translate(x = 87f, y = 0f)

        val path = Path()
        path.moveTo(start.x, start.y)
        path.cubicTo(control1.x, control1.y, control2.x, control2.y, middle.x, middle.y)
        path.cubicTo(control3.x, control3.y, control4.x, control4.y, end.x, end.y)

        // افزودن یک curve بالای دایره به تابع createCircalPath
        val curveStartX = end.x - radius
        val curveEndX = end.x + radius
        val curveStartY = end.y - radius / 2
        val curveEndY = end.y - radius
        path.moveTo(curveStartX, curveStartY)
        path.cubicTo(
            curveStartX, end.y - radius / 4,
            curveEndX, end.y - radius / 4,
            curveEndX, curveEndY
        )

        return path
    }
}