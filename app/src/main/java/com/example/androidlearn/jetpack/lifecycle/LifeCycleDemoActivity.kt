package com.example.androidlearn.jetpack.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 12:27 2022/11/25
 */
class LifeCycleDemoActivity : AppCompatActivity() {
    private val player = NbPlayer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //注册生命周期观察者
        lifecycle.addObserver(player)
    }
}