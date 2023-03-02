package com.example.androidlearn.customview

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import android.widget.Scroller

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 17:18 2023/2/23
 */
class ScrollableView @JvmOverloads constructor(
    context: Context,
    attributes: AttributeSet? = null,
    defStyle: Int = -1
) : androidx.appcompat.widget.AppCompatTextView(
    context,
    attributes,
    defStyle
) {
    companion object {
        private const val TAG = "ScrollableView"
    }

    private val scroller by lazy { Scroller(context) }

    override fun computeScroll() {
        super.computeScroll()
        if (scroller.computeScrollOffset()) {
            (parent as ViewGroup).scrollBy(scroller.currX, scroller.currY)
            invalidate()
        }
    }

    fun startScroll(deltaX: Int, deltaY: Int) {
        scroller.startScroll(scrollX, scrollX - deltaX, scrollY, scrollY - deltaY)
        invalidate()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d(TAG, "onMeasure: ffffffffffffff")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.d(TAG, "onLayout: eeeeeeeeeeeeee")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d(TAG, "onDraw: kkkkkkkkkkkkkkkkkkk")
    }
}