package com.example.androidlearn

import android.app.Application
import android.util.Log
import com.tencent.mmkv.MMKV

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
        MMKV.defaultMMKV().encode("myBook", Book("第一行代码", 1))
        val book = MMKV.defaultMMKV().decodeParcelable("myBook", Book::class.java)
        Log.d(TAG,"my book name is ${book?.name}")
    }

}