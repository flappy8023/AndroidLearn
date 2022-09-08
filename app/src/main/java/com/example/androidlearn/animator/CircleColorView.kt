package com.example.androidlearn.animator

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 10:06 2022/9/6
 */
class CircleColorView : View {
    constructor(context: Context) : super(context)

    constructor(context: Context, attr: AttributeSet?) : super(context, attr)

    val path: Path by lazy {
        Path().apply {
            addOval(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat(), Path.Direction.CW)
        }
    }
    var color = Color.GRAY
        set(value) {
            field = value
            invalidate()
        }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        canvas?.clipPath(path)
        canvas?.drawColor(color)
    }
}