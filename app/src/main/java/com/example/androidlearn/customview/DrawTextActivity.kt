package com.example.androidlearn.customview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidlearn.R
import com.example.androidlearn.databinding.ActivityDrawTextBinding
import com.flappy.wanandroid.base.BaseActivity

class DrawTextActivity : BaseActivity<ActivityDrawTextBinding>() {

    override fun initView() {
        binding.tv1
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_draw_text
    }
}