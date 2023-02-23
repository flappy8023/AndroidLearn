package com.example.androidlearn.jetpack.lifecycle

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

/**
 * @Author: luweiming
 * @Description:
 * @Date: Created in 12:24 2022/11/25
 */
class NbPlayer : DefaultLifecycleObserver {
    fun init() {}
    fun play() {}
    fun pausePlay() {}
    fun release() {}

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        //初始化
        init()
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        play()
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        pausePlay()
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        release()
    }
}