package com.example.androidlearn.kotlin.flow

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.androidlearn.R
import com.example.androidlearn.databinding.ActivityFlowTestBinding
import kotlinx.coroutines.launch

class FlowTestActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "FlowTestActivity"
    }

    private val viewModel by viewModels<FlowTestViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityFlowTestBinding>(
            this,
            R.layout.activity_flow_test
        )
        lifecycleScope.launch {
            //冷流不同的消费者互不影响，仅在collect时才开始生产数据
            viewModel.timer2.collect {
                Log.d(TAG, "onCreate: $it")
            }
            viewModel.timer2.collect {
                Log.d(TAG, "onCreate: --->$it")
            }
        }
    }
}