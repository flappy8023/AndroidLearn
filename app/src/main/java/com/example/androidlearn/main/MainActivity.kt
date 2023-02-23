package com.example.androidlearn.main

import android.content.Intent
import android.os.Looper
import android.util.Log
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlearn.R
import com.example.androidlearn.backstate.BackgroundActivity
import com.example.androidlearn.bitmap.BitmapCompressActivity
import com.example.androidlearn.databinding.ActivityMainBinding
import com.example.androidlearn.fragment.FragmentTestActivity
import com.example.androidlearn.ipc.IPCActivity
import com.example.androidlearn.jetpack.JetpackActivity
import com.example.androidlearn.kotlin.KotlinFeatureActivity
import com.example.androidlearn.multithread.ThreadActivity
import com.example.androidlearn.security.SecurityActivity
import com.flappy.wanandroid.base.BaseActivity
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
        val list = getData()
        adapter.onItemClick = this::click
        adapter.addAll(list)
    }

    open fun getData(): List<String> {
        return listOf(
            "自定义View",
            "动画",
            "多线程",
            "进程间通信",
            "加解密",
            "前后台",
            "Fragment",
            "Jetpack",
            "Kotlin特性",
            "图片加载"
        )
    }


    open fun click(position: Int) {
        when (position) {
            0 -> startCoroutine()
            1 -> stopCoroutine()
            2 -> startAct(ThreadActivity::class.java)
            3 -> startAct(IPCActivity::class.java)
            4 -> startAct(SecurityActivity::class.java)
            5 -> startAct(BackgroundActivity::class.java)
            6 -> startAct(FragmentTestActivity::class.java)
            7 -> startAct(JetpackActivity::class.java)
            8 -> startAct(KotlinFeatureActivity::class.java)
            9 -> startAct(BitmapCompressActivity::class.java)
        }
    }

    lateinit var job: Job
    fun startCoroutine() {
        job = lifecycleScope.launch {
            while (true) {
                delay(1000)
                Log.d("ggggggggg", "1111")
            }
        }
    }

    fun stopCoroutine() {
        job.cancel()
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    fun startAct(cls: Class<*>) {
        startActivity(Intent(this, cls))
    }
}