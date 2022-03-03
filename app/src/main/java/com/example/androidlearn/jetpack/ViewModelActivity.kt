package com.example.androidlearn.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.androidlearn.R

class ViewModelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
        ViewModelProvider(this).get(MyViewModel::class.java).text.observe(this){
//            findViewById<TextView>(R.id.tv).text = it
        }
        findViewById<Button>(R.id.bt).setOnClickListener {
            findViewById<TextView>(R.id.tv).text = "333"
        }
    }
}
