package com.example.androidlearn.customview

import com.example.androidlearn.main.MainActivity

class CustomViewActivity : MainActivity() {
    override fun getData(): List<String> {
        return listOf("绘制文字", "圆角View")
    }

    override fun click(position: Int) {
        when(position){
            0 -> startAct(DrawTextActivity::class.java)
            1 -> startAct(RoundViewActivity::class.java)
        }
    }
}