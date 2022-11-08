package com.example.androidlearn.kotlin.coroutine

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.androidlearn.R
import com.example.androidlearn.databinding.ActivityCoroutineBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*

class CoroutineActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "CoroutineActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityCoroutineBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_coroutine)
        var time = 0
        var job: Job? = null
        binding.btStart.setOnClickListener {
            job = CoroutineScope(Dispatchers.Main).launch {
                while (true) {
                    delay(1000)
                    binding.tvResult.text = "coroutine is running,times:${time++}"
                }
            }
        }
        binding.btStop.setOnClickListener {
            job?.cancel()
        }
        val deferred1 = lifecycleScope.async {
            for (i in 0..100) {
                delay((Math.random() * 500).toLong())
                binding.progressStart.progress = i
            }
            100
        }
        val deferred2 = lifecycleScope.async {
            for (i in 0..100) {
                delay((Math.random() * 500).toLong())
                binding.progressEnd.progress = i
            }
            100
        }
        binding.btAwait.setOnClickListener {
            lifecycleScope.launch {
                deferred1.await()
                deferred2.await()
                Snackbar.make(binding.btAwait, "两个都结束了", Snackbar.LENGTH_LONG).show()
            }
        }
    }

}