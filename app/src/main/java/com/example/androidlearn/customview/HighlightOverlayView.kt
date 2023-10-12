package com.example.androidlearn.customview

/**
 * @Author flappy8023
 * @Description //TODO
 * @Date 2023年08月15日 11:15
 **/
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class HighlightOverlayView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val maskPaint = Paint().apply {
        color = Color.parseColor("#80000000") // 半透明黑色，用于蒙层
    }

    private val clearPaint = Paint().apply {
        color = Color.TRANSPARENT
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR) // 使用CLEAR模式实现挖洞
        isAntiAlias = true
    }

    private val highlightRect = Rect()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // 绘制蒙层
        canvas.drawRect(0f, 0f, width.toFloat(), height.toFloat(), maskPaint)

        // 计算高亮区域的位置和大小
        val quarterWidth = width / 4
        val quarterHeight = height / 4
        val centerX = width / 2
        val centerY = height / 2
        highlightRect.set(
            centerX - quarterWidth,
            centerY - quarterHeight,
            centerX + quarterWidth,
            centerY + quarterHeight
        )

        // 使用CLEAR模式挖洞
        canvas.drawRect(highlightRect, clearPaint)
    }
}



