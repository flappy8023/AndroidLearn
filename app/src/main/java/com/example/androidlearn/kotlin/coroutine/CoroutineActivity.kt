package com.example.androidlearn.kotlin.coroutine

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.androidlearn.R
import com.example.androidlearn.databinding.ActivityCoroutineBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

class CoroutineActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "CoroutineActivity"
    }
    private val binding:ActivityCoroutineBinding by lazy { ActivityCoroutineBinding.inflate(layoutInflater) }

    private val str: String by lazy(LazyThreadSafetyMode.NONE) { "" }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
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

        binding.btAwait.setOnClickListener {
            testAsync()
        }
        testSuspendCoroutine()


    }

    private fun testAsync(){
        lifecycleScope.launch {
            val deferred1 = async {
                for (i in 0..100) {
                    delay((Math.random() * 100).toLong())
                    binding.progressStart.progress = i
                }
                100
            }
            val deferred2 = async {
                for (i in 0..100) {
                    delay((Math.random() * 100).toLong())
                    binding.progressEnd.progress = i
                }
                100
            }
            deferred1.await()
            deferred2.await()
            Snackbar.make(binding.btAwait, "两个都结束了", Snackbar.LENGTH_LONG).show()
        }
    }

    @OptIn(ExperimentalTime::class)
    private fun testSuspendCoroutine() {
        lifecycleScope.launch {
            val time = measureTime {
                val a = suspendCancellableCoroutine<String> { continuation ->
                    asyncFun1 { continuation.resume(it) }
                }
                val b = suspendCancellableCoroutine<String> { continuation ->
                    asyncFun2 { continuation.resume(it) }

                }
                Log.d(TAG, "$a $b")
            }
            Log.d(TAG, "testSuspendCoroutine: measureTime = $time")
        }
        lifecycleScope.launch {
            val time = measureTime {
                val deffered1 = async {
                    suspendCancellableCoroutine<String> { continuation ->
                        asyncFun1 { continuation.resume(it) }
                    }
                }

                val deffered2 = async {
                    suspendCancellableCoroutine<String> { continuation->
                        asyncFun2 { continuation.resumeWith(Result.success(it)) }
                    }
                }

                Log.d(TAG, "${deffered1.await()} ${deffered2.await()}")
            }
            Log.d(TAG, "testSuspendCoroutine async: measureTime = $time")
        }
    }


    private fun asyncFun1(callback: (String) -> Unit) {
        thread {
            Thread.sleep(2000)
            runOnUiThread {
                callback("hello")

            }
        }
    }

    private fun asyncFun2(callback: (String) -> Unit) {
        //模拟耗时操作
        thread {
            Thread.sleep(5000)
            runOnUiThread {
                callback("world")

            }
        }
    }

}