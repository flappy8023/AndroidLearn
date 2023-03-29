package com.example.androidlearn.performance

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.R

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 10:47 2023/3/28
 */
class LeakTestActivity : AppCompatActivity(R.layout.act_leak_test) {

    private val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            resources.displayMetrics.scaledDensity
            findViewById<TextView>(R.id.tv_hello).text = "ffff"
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handler.sendEmptyMessageDelayed(10, 20_000)
    }

}