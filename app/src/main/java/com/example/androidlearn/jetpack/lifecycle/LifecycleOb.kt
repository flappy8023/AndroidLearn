package com.example.androidlearn.jetpack.lifecycle

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * @Author: luweiming
 * @Description: TODO
 * @Date: Created in 9:56 2022/3/11
 */
class LifecycleOb:DefaultLifecycleObserver {
    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        GlobalScope.launch {
            while (owner.lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)){
                delay(200)
                Log.d("ffff","CREATED")
            }
            if(Lifecycle.State.DESTROYED==owner.lifecycle.currentState){
                Log.e("ffff","I am Destroyed")
            }
        }

    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
    }

    override fun onPause(owner: LifecycleOwner) {
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
    }

}