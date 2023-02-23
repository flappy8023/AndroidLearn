package com.example.androidlearn.customview

import android.content.Context
import android.util.AttributeSet
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
}