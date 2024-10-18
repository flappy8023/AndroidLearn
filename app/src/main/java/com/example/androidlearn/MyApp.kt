package com.example.androidlearn

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import com.tencent.mmkv.MMKV
import dagger.hilt.android.HiltAndroidApp

/**
 * @Author: luweiming
 * @Description: TODO
 * @Date: Created in 15:46 2022/3/25
 */
@HiltAndroidApp
class MyApp : Application() {
    private val TAG = "MyApp"
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var myContext:Context
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        myContext = base!!
    }
    override fun onCreate() {
        super.onCreate()

        val dir = MMKV.initialize(this)
        Log.d(TAG, "mmkv root dir:$dir")
    }

}