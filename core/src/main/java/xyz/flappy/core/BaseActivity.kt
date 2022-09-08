package com.flappy.wanandroid.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * @Author: luweiming
 * @Description:activity基类，使用时需要指定ViewDataBinding和ViewModel
 * @Date: Created in 12:48 2022/8/21
 */
abstract class BaseActivity<VB : ViewDataBinding> : AppCompatActivity() {
    lateinit var binding: VB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initViewBinding()
        initView()
    }


    /**
     * 初始化viewBinding
     */
    private fun initViewBinding() {
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        binding.lifecycleOwner = this
    }


    /**
     * 初始化视图
     */
    abstract fun initView()

    /**
     * 获取布局文件资源id
     * @return Int
     */
    abstract fun getLayoutId(): Int

}