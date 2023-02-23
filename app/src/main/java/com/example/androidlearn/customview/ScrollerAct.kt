package com.example.androidlearn.customview

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.R

/**
 * @Author: luweiming
 * @Description: View滑动
 * @Date: Created in 14:35 2023/2/23
 */
class ScrollerAct : AppCompatActivity() {
    private var lastX = 0f
    private var lastY = 0f
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_scroller)
        val myView = findViewById<ScrollableView>(R.id.my_view)
        myView.setOnTouchListener { v, event ->
            val x = event.rawX
            val y = event.rawY
            when (event.action) {

                MotionEvent.ACTION_DOWN -> {

                }
                MotionEvent.ACTION_MOVE -> {
                    val deltX = x - lastX
                    val deltY = y - lastY
                    scrollView(v, deltX.toInt(), deltY.toInt())
                }
            }
            lastX = x
            lastY = y
            true
        }
    }

    private fun scrollView(view: View, deltaX: Int, deltaY: Int) {
        //1.使用layout
//        view.layout(
//            view.left + deltaX,
//            view.top + deltaY,
//            view.right + deltaX,
//            view.bottom + deltaY
//        )
        //2.修改layoutParam
//        val params = (view.layoutParams as ViewGroup.MarginLayoutParams).apply {
//            leftMargin += deltaX
//            topMargin += deltaY
//        }
//        view.layoutParams = params

        //3.offsetLeftAndRight
//        view.offsetLeftAndRight(deltaX)
//        view.offsetTopAndBottom(deltaY)

        //4.scroller
//        (view as ScrollableView).startScroll(deltaX,deltaY)

        //5. scrollTo/ScrollBy
        (view.parent as ViewGroup).scrollBy(-deltaX, -deltaY)
    }


}