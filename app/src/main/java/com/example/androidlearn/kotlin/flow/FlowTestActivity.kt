package com.example.androidlearn.kotlin.flow

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.androidlearn.R
import com.example.androidlearn.databinding.ActivityFlowTestBinding
import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

class FlowTestActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "FlowTestActivity"
    }

    private val viewModel by viewModels<FlowTestViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory()).get(
            FlowTestViewModel::class.java
        );
        val binding = DataBindingUtil.setContentView<ActivityFlowTestBinding>(
            this,
            R.layout.activity_flow_test
        )
        lifecycleScope.launch {
            //冷流不同的消费者互不影响，仅在collect时才开始生产数据
            viewModel.timer2.collect {
                Log.d(TAG, "onCreate: $it")
            }

        }
        lifecycleScope.launch {
            delay(3000)
            viewModel.timer2.collect {
                Log.d(TAG, "onCreate: --->$it")
            }
        }
        lifecycleScope.launch {
            val time = measureTimeMillis {
                val deferred1 = async { fetch1() }
                val deferred2 = async { fetch2() }
                val list = listOf(deferred1, deferred2).awaitAll()
//                Log.d(TAG, "onCreate: $list ")
            }
            Log.d(TAG, "onCreate: time = $time")
        }
        val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e(
                "fffffff",
                "got exception:${throwable.message}"
            )
        }
        lifecycleScope.launch(exceptionHandler) {
            Log.e("Ffff", "i will throw an exception")
            launch {
                throw NullPointerException("sub coroutine throw NPE")
            }
            delay(100)
            throw java.lang.NullPointerException("hello NPE")
        }
        thread {
//            Looper.prepare()
//            val dialog = AlertDialog.Builder(this).apply {
//                setTitle("hello")
//                setNegativeButton("cancel") { d, _ -> d.dismiss() }
//            }
//            dialog.show()
//            Looper.loop()

        }
    }

    private suspend fun fetch1(): String {
        Log.d(TAG, "fetch1: begin")
        delay(2000)
        return "fetch1 done"
    }

    private suspend fun fetch2(): String {
        Log.d(TAG, "fetch2: begin")
        delay(5000)
        return "fetch2 done"
    }
}