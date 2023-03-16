package com.example.androidlearn

import android.app.Application
import android.util.Log
import com.example.androidlearn.di.myModule
import com.tencent.mmkv.MMKV
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

/**
 * @Author: luweiming
 * @Description: TODO
 * @Date: Created in 15:46 2022/3/25
 */
class MyApp : Application() {
    private val TAG = "MyApp"
    override fun onCreate() {
        super.onCreate()

        val dir = MMKV.initialize(this)
        Log.d(TAG, "mmkv root dir:$dir")
        startKoin {
            androidLogger()
            androidContext(this@MyApp)
            modules(myModule)
        }
    }

}