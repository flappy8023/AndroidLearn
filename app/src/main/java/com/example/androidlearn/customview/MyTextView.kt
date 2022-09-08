package com.example.androidlearn.customview

import android.content.Context
import android.graphics.*
import android.os.Build
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 13:30 2022/9/8
 */
class MyTextView : View {
    companion object {
        private const val TAG = "MyTextView"
    }

    val paint: Paint = Paint()

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)

    init {
        paint.textSize = 26f
        paint.style = Paint.Style.STROKE
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        //来个背景衬托
        canvas?.drawColor(Color.LTGRAY)
        //普通文字drawText,y代表基线位置，x也不是文字左边，包含文字间隔
        paint.shader =
            LinearGradient(0f, 0f, 100f, 100f, Color.BLUE, Color.RED, Shader.TileMode.MIRROR)
        canvas?.drawText("drawText普通绘制文字", 40f, 40f, paint)
        //drawOnPath,根据路径绘制文字
        paint.shader = null
        paint.color = Color.BLACK
        val path = Path()
        path.moveTo(40f, 100f)
        path.cubicTo(150f, 100f, 250f, 0f, 600f, 200f)
        canvas?.drawPath(path, paint)
        canvas?.drawTextOnPath("drawTextOnPath在路径上绘制文字", path, 0f, 0f, paint)
        paint.setTypeface(Typeface.SERIF)
        canvas?.drawText("Paint.setTypeFace设置字体", 40f, 240f, paint)
        paint.textAlign = Paint.Align.RIGHT
        paint.typeface = Typeface.DEFAULT
        canvas?.drawText(
            "setTextAlign设置文字对齐方式,以x,y为原点",
            measuredWidth.toFloat() - 100f,
            300f,
            paint
        )
        paint.textAlign = Paint.Align.LEFT
        val s = "StaticLayout可以绘制多行文字，并处理换行，StaticLayout.draw(canvas)\nabc\ndef"
        val textPaint = TextPaint()
        textPaint.color = Color.BLUE
        textPaint.textSize = 26f
        textPaint.bgColor = Color.GRAY
        val staticLayout = StaticLayout.Builder.obtain(s, 0, s.length, textPaint, 600).build()
        canvas?.save()
        canvas?.translate(40f, 350f)
        staticLayout.draw(canvas)
        canvas?.restore()
        canvas?.drawText(
            "Paint.getFontSpacing获取推荐的行距，即两个baseLine之间的距离,目前是：${paint.fontSpacing}",
            40f,
            500f,
            paint
        )
        val metrics = paint.fontMetrics
        val str1 =
            "Paint.getFontMetrics获取文字排印方面的数值：top:${metrics.top}、ascent:${metrics.ascent}、descent:${metrics.descent}、bottom:${metrics.bottom}、leading:${metrics.leading}"
        val staticLayout1 =
            StaticLayout.Builder.obtain(str1, 0, str1.length, textPaint, measuredWidth - 80)
                .build()
        canvas?.save()
        canvas?.translate(40f, 550f)
        staticLayout1.draw(canvas)
        canvas?.restore()

        val str = "Paint.getTextBounds获取文字显示范围,根据范围和偏移量我会有一个背景框"
        paint.color = Color.BLACK
        val rect = Rect()
        paint.getTextBounds(str, 0, str.length, rect)
        //加上现在的偏移量
        rect.apply {
            top += 700
            left += 40
            right += 40
            bottom += 700
        }
        paint.style = Paint.Style.FILL
        paint.color = Color.WHITE
        canvas?.drawRect(rect, paint)
        paint.style = Paint.Style.STROKE
        paint.color = Color.BLACK
        canvas?.drawText(str, 40f, 700f, paint)

        Log.d(
            TAG,
            "getMeasureText测得文字宽度：${paint.measureText(str)},getTextBounds测得宽度：${rect.right - rect.left}"
        )


    }

}