package com.example.androidlearn.ipc.aidl

import android.util.Log
import com.example.androidlearn.IUserInterface

/**
 * description:
 * date: 2022/3/5 21:51
 * author: luweiming
 * version: 1.0
 */
class IUserImpl : IUserInterface.Stub() {

    override fun sayHello() {
        Log.d("fff", "hello world")
    }
}