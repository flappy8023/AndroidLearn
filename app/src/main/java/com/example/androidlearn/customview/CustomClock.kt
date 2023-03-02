package com.example.androidlearn.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 9:38 2023/2/24
 */
class CustomClock @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attributeSet, defStyle) {
    val linePaint = Paint()
    val textPaint = Paint()

    init {
        linePaint.apply {
            isAntiAlias = true
            color = Color.BLUE
            strokeWidth = 6f
        }
        textPaint.apply {
            isAntiAlias = true
            color = Color.RED
            textSize = 18f
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(400, 400)
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(400, heightSize)
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, 400)
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.LTGRAY)
        canvas?.translate(width / 2f, height / 2f)
        canvas?.save()
        for (i in 0..15) {
            canvas?.drawLine(0f, -width / 2f, 0f, -width / 2f + 20, linePaint)
            canvas?.rotate(6f)
        }
        canvas?.restore()
        canvas?.drawText("我的表", 0f, 0f, textPaint)
    }
}
