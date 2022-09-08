package com.example.androidlearn.animator

import android.animation.ObjectAnimator
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.R

class EvaluatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_evelator)
        val view1 = findViewById<CircleColorView>(R.id.view1)
        val view2 = findViewById<CircleColorView>(R.id.view2)
        val objAnimator =
            ObjectAnimator.ofArgb(view1, "color", 0xffff0000.toInt(), 0xff00ff00.toInt())
        objAnimator.duration = 2000
        val argbAnimator =
            ObjectAnimator.ofInt(view2, "color", 0xffff0000.toInt(), 0xff00ff00.toInt())
        argbAnimator.setEvaluator(HsvEvaluator())
        argbAnimator.duration = 2000
        findViewById<Button>(R.id.bt_start).setOnClickListener {
            objAnimator.start()

            argbAnimator.start()

        }
    }
}