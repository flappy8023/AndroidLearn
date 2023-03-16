package com.example.androidlearn.di

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import org.koin.android.ext.android.inject

/**
 * @Author: luweiming
 * @Description: koin依赖注入
 * @Date: Created in 10:14 2023/3/16
 */
class HomeActivity : AppCompatActivity() {
    val viewModel: HomeViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenStarted {
            viewModel.flow.collect {
                Toast.makeText(this@HomeActivity, it.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.getData()
    }
}