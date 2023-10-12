package com.example.androidlearn.customview

import com.example.androidlearn.main.MainActivity

class CustomViewActivity : MainActivity() {
    override fun getData() = mapOf(
        "绘制文字" to DrawTextActivity::class.java,
        "圆角View" to RoundViewActivity::class.java,
        "View滑动" to ScrollerAct::class.java,
        "区域高亮" to MaskActivity::class.java,
        "时钟" to ClockAct::class.java
    )


}