package com.example.androidlearn.ipc.messenger

import android.app.Service
import android.content.Intent
import android.os.*
import android.util.Log

/**
 * description:
 * date: 2022/3/3 22:16
 * author: luweiming
 * version: 1.0
 */
class MessengerRemoteService : Service() {
    override fun onCreate() {
        super.onCreate()
        Log.d("fff", "service create")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("fff", "service onStartCommend")
        return super.onStartCommand(intent, flags, startId)

    }

    private var handler: Handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                10 -> {
                    Log.d(
                        "fff", "receive client's msg:" + msg.data.getString("msg")

                    )
                    msg.replyTo.send(Message.obtain().apply {
                        what = 11
                        data = Bundle().apply { putString("msg","hello client!") }
                    })
                }
            }
        }
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d("fff", "service unbind")
        return super.onUnbind(intent)
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d("fff", "service onBind")
        return Messenger(handler).binder
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("fff", "service onDestroy")
    }
}