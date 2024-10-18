package com.example.androidlearn.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.util.AttributeSet
import android.view.View

/**
 * @Author flappy8023
 * @Description 高亮显示区域
 * @Date 2023年08月14日 10:41
 **/
class VisibleAreaMaskView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : View(context, attributeSet, defStyle) {
    private val bgColor = Color.TRANSPARENT

    /**
     * 垂直方向高亮区域距离边缘的padding
     */
    private val verticalPadding = 10

    /**
     * 高亮区域描边宽度
     */
    private val hStrokeWidth = 10

    /**
     * 区域之间的间隔
     */
    private val interval = 8

    /**
     * 分割后的小矩形个数
     */
    private val divisionCount = 11

    /**
     * 高亮区域由多少个小矩形组成
     */
    private val areaConsistOf = 5

    /**
     * 当前高亮区域的位置，默认区域四
     */
    private var selectIndex = 3

    /**
     * 小矩形的宽度,排除间隔
     */
    private var rectWidth = 0
    private val paint = Paint()


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //计算单个矩形的宽度,排除间隔
        rectWidth = (measuredWidth - divisionCount * interval) / divisionCount
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawBackground(canvas)
        drawHighlightArea(canvas)
    }

    private fun drawHighlightArea(canvas: Canvas) {
        paint.apply {
            color = bgColor
            style = Paint.Style.FILL
            isAntiAlias = true
        }
        canvas?.drawRect(0f, 0f, width.toFloat(), height.toFloat(), paint)
        val saveLayer = canvas?.saveLayer(0f,0f,width.toFloat(),height.toFloat(),paint)
        //描边的左侧位置
        val strokeLeft = (selectIndex + 1) * interval + selectIndex * rectWidth.toFloat()
        val strokeTop = verticalPadding.toFloat()
        val strokeBottom = height - verticalPadding.toFloat()
        val strokeRight =
            strokeLeft + (areaConsistOf - 1) * interval + rectWidth * areaConsistOf.toFloat()
        //绘制高亮区域

        paint.apply {
            color = Color.parseColor("#80000000")
            isAntiAlias = true
            xfermode =PorterDuffXfermode(PorterDuff.Mode.DST_OUT)
            style = Paint.Style.FILL
        }
        canvas?.drawRect(strokeLeft, strokeTop, strokeRight, strokeBottom, paint)
        val path = Path()
        path.apply {
            moveTo(strokeLeft, strokeTop)
            lineTo(strokeLeft, strokeBottom)
            lineTo(strokeRight, strokeBottom)
            lineTo(strokeRight, strokeTop)
            close()
        }
        //绘制描边
        paint.apply {
            color = Color.WHITE
            isAntiAlias = true
            xfermode = null
            style = Paint.Style.STROKE
            strokeWidth = hStrokeWidth.toFloat()
        }
        canvas?.drawPath(path, paint)
        canvas?.restoreToCount(saveLayer!!)


    }

    private fun drawBackground(canvas: Canvas?) {

    }

    fun selectArea(index: Int) {
        selectIndex = index
        invalidate()
    }
}