package com.example.androidlearn.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidlearn.main.MainActivity

class CustomViewActivity : MainActivity() {
    override fun getData(): List<String> {
        return listOf("绘制文字")
    }

    override fun click(position: Int) {
        when(position){
            0->startAct(DrawTextActivity::class.java)
        }
    }
}