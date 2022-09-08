package com.example.androidlearn.ipc.messenger

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.androidlearn.databinding.ActivityMessenger2Binding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MessengerActivity : AppCompatActivity() {
    lateinit var binding: ActivityMessenger2Binding
    private lateinit var messenger: Messenger

    class MyHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                11 -> {
                    Log.d("fff", "receive server's replay:" + msg.data.getString("msg"))
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessenger2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        GlobalScope.launch {
            delay(3000)
            Log.e("fff",Thread.currentThread().name)
        }
        val conn: ServiceConnection = object : ServiceConnection {
            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                Log.d("fff", "onConnect")
                messenger = Messenger(service)
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                Log.d("fff", "onDisconnect")
            }
        }
        binding.bt1.setOnClickListener {
            startService(Intent(this, MessengerRemoteService::class.java))
        }
        binding.bt2.setOnClickListener {
            stopService(Intent(this, MessengerRemoteService::class.java))
        }
        binding.bt3.setOnClickListener {
            bindService(
                Intent(this, MessengerRemoteService::class.java),
                conn,
                Context.BIND_AUTO_CREATE
            )
        }
        binding.bt4.setOnClickListener {
            unbindService(conn)
        }
        binding.bt5.setOnClickListener {
            messenger?.let {
                it.send(Message.obtain().apply {
                    what = 10
                    //需要接受回复消息
                    replyTo = Messenger(MyHandler())
                    data = Bundle().apply { putString("msg", "hello server!") }
                })
            }
        }
    }
}