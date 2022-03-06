package com.example.androidlearn.ipc.aidl

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.androidlearn.IBinderPool

class BinderPoolService : Service() {

    override fun onBind(intent: Intent): IBinder {
        return iBinderPoolImpl
    }

    private val iBinderPoolImpl = object : IBinderPool.Stub() {
        override fun queryBinder(code: Int): IBinder {
            when (code) {
                11 -> {
                    return IBookImpl()
                }
                12 -> return IUserImpl()
            }
            return IBookImpl()
        }
    }
}