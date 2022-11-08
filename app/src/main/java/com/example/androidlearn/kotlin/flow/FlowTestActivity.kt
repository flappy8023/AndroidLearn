package com.example.androidlearn.kotlin.flow

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.androidlearn.R
import com.example.androidlearn.databinding.ActivityFlowTestBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FlowTestActivity : AppCompatActivity() {
    private val viewModel by viewModels<FlowTestViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityFlowTestBinding>(
            this,
            R.layout.activity_flow_test
        )
        lifecycleScope.launch {
            launch {
                viewModel.timer.collect {
                    binding.tv1.text = "collect:$it"
                    delay(3000)
                }
                Log.d("ffff","ffffffffffff")
            }
//            launch {
//                viewModel.timer.collect {
//                    //此处不会被执行
//                    binding.tv1.text = "fffffffffffffff"
//                }
//            }
            launch {
                viewModel.timer1.collectLatest {
                    binding.tv2.text = "collectLatest:$it"
                    delay(3000)
                }
            }
        }
    }
}