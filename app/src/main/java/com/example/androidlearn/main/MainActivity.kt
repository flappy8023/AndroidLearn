package com.example.androidlearn.main

import android.content.Intent
import android.os.Looper
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlearn.R
import com.example.androidlearn.animator.AnimatorActivity
import com.example.androidlearn.bitmap.BitmapActivity
import com.example.androidlearn.customview.CustomViewActivity
import com.example.androidlearn.databinding.ActivityMainBinding
import com.example.androidlearn.di.HomeActivity
import com.example.androidlearn.fragment.FragmentTestActivity
import com.example.androidlearn.ipc.IPCActivity
import com.example.androidlearn.jetpack.JetpackActivity
import com.example.androidlearn.kotlin.KotlinFeatureActivity
import com.example.androidlearn.multithread.ThreadActivity
import com.example.androidlearn.security.SecurityActivity
import com.flappy.wanandroid.base.BaseActivity
import kotlinx.coroutines.Job

open class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val adapter by lazy { MainAdapter() }
    override fun initView() {
        Looper.getMainLooper().setMessageLogging { Log.d("ffffffff", it) }
        binding.rvMain.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = this@MainActivity.adapter
        }
        initData()
    }

    private fun initData() {
        val list = getData().keys.toList()
        adapter.onItemClick = {
            val target = getData()[list[it]]!!
            startAct(target)
        }
        adapter.addAll(list)
    }

    open fun getData(): Map<String, Class<*>> {
        return mapOf(
            "自定义View" to CustomViewActivity::class.java,
            "动画" to AnimatorActivity::class.java,
            "多线程" to ThreadActivity::class.java,
            "进程间通信" to IPCActivity::class.java,
            "加解密" to SecurityActivity::class.java,
            "依赖注入" to HomeActivity::class.java,
            "Fragment" to FragmentTestActivity::class.java,
            "Jetpack" to JetpackActivity::class.java,
            "Kotlin特性" to KotlinFeatureActivity::class.java,
            "图片加载" to BitmapActivity::class.java,
            "Fragment" to FragmentTestActivity::class.java
        )
    }


    lateinit var job: Job


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    fun startAct(cls: Class<*>) {
        startActivity(Intent(this, cls))
    }
}