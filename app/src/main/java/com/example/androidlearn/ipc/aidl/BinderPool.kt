package com.example.androidlearn.ipc.aidl

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.example.androidlearn.IBinderPool
import java.util.concurrent.CountDownLatch

/**
 * description:
 * date: 2022/3/5 21:32
 * author: luweiming
 * version: 1.0
 */
class BinderPool {
    private lateinit var context: Context
    private lateinit var countDownLatch: CountDownLatch
    private var iBinderPool: IBinderPool? = null

    companion object {
        @Volatile
        var instance: BinderPool? = null
        fun getInstance(context: Context): BinderPool {

            if (null == instance) {
                synchronized(this) {
                    if (null == instance) {
                        instance = BinderPool(context)

                    }
                }
            }
            return instance!!
        }
    }

    private constructor(context: Context) {
        this.context = context
        connectBinderPoolService()
    }

    private val dethRecipient: IBinder.DeathRecipient = object : IBinder.DeathRecipient {

        override fun binderDied() {
            iBinderPool?.asBinder()?.unlinkToDeath(this, 0)
            iBinderPool = null
            connectBinderPoolService()
        }
    }

    private val conn = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            iBinderPool = IBinderPool.Stub.asInterface(service)
            iBinderPool?.asBinder()?.linkToDeath(dethRecipient, 0)
            countDownLatch.countDown()
        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }
    }

    private fun connectBinderPoolService() {
        countDownLatch = CountDownLatch(1)
        context.bindService(
            Intent(context, BinderPoolService::class.java),
            conn,
            Context.BIND_AUTO_CREATE
        )
        countDownLatch.await()

    }

    fun queryBinder(code: Int): IBinder? {
        if (null != iBinderPool) {
            return iBinderPool?.queryBinder(code)
        }
        return null
    }
}