package com.example.androidlearn.window

import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.widget.Button
import com.example.androidlearn.Book
import com.example.androidlearn.R
import com.example.androidlearn.databinding.ActivityFloatingButtonBinding
import com.example.androidlearn.remoteviews.NotificationActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class FloatingButtonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFloatingButtonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFloatingButtonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val windowManager:WindowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        binding.btApp.setOnClickListener {
            val button = Button(this)
            button.text = "hellow,app"
            val params = WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_APPLICATION,0,PixelFormat.TRANSPARENT)
            params.apply {
                flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                gravity = Gravity.BOTTOM or Gravity.START
                x=100
                y=200
            }
            windowManager.addView(button,params)
        }
        binding.btSub.setOnClickListener {
            val button = Button(this)
            button.text = "hellow,app"
            val params = WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.FIRST_SUB_WINDOW,0,PixelFormat.TRANSPARENT)
            params.apply {
                flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                gravity = Gravity.BOTTOM or Gravity.START
                x=100
                y=400
            }
            windowManager.addView(button,params)
        }

        binding.btSys.setOnClickListener {
            val button = Button(this)
            button.setOnClickListener {
                startActivity(Intent(this,NotificationActivity::class.java))
            }
            button.text = "hellow,app"
            var params = WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,0,PixelFormat.TRANSPARENT)
            params.apply {
                flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                gravity = Gravity.TOP or Gravity.START
            }
            button.setOnTouchListener(object :View.OnTouchListener{
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    val rawX = event!!.rawX
                    val rawY = event!!.rawY
                    when(event.action){
                        MotionEvent.ACTION_MOVE->{
                            params.x = rawX.toInt()
                            params.y = rawY.toInt()
                            windowManager.updateViewLayout(button,params)
                        }
                    }
                    return false
                }
            })
            windowManager.addView(button,params)
        }
    }
}