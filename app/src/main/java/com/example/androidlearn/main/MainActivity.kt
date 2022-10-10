package com.example.androidlearn.main

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidlearn.R
import com.example.androidlearn.animator.AnimatorActivity
import com.example.androidlearn.customview.CustomViewActivity
import com.example.androidlearn.databinding.ActivityMainBinding
import com.example.androidlearn.ipc.IPCActivity
import com.example.androidlearn.security.SecurityActivity
import com.flappy.wanandroid.base.BaseActivity

open class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val adapter by lazy { MainAdapter() }
    override fun initView() {
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
        return listOf("自定义View","动画","进程间通信","加解密")
    }

    open fun click(position: Int) {
        when (position) {
            0 -> startAct(CustomViewActivity::class.java)
            1 -> startAct(AnimatorActivity::class.java)
            2 -> startAct(IPCActivity::class.java)
            3 -> startAct(SecurityActivity::class.java)
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    fun startAct(cls: Class<*>) {
        startActivity(Intent(this, cls))
    }
}