package com.example.androidlearn.jetpack.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidlearn.R

class LifecycleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycle)
        lifecycle.addObserver(LifecycleOb())
    }
}