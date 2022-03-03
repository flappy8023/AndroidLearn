package com.example.androidlearn.thread

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import com.example.androidlearn.R

class MainActivity : AppCompatActivity() {
    private  val TAG = "FFFFFFFFFFFF"
    lateinit var hander:Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val thread1 = object :Runnable{
            override fun run() {
                Looper.prepare()
                hander = Handler(Looper.myLooper()!!,object :Handler.Callback{
                    override fun handleMessage(msg: Message): Boolean {
                        Log.d(TAG,"thread-${Thread.currentThread().name}:handle message:msg.what = ${msg.what} ,msg.obj = ${msg.obj}")
                        return true;
                    }

                })
                Looper.myQueue().addIdleHandler(object :MessageQueue.IdleHandler{
                    override fun queueIdle(): Boolean {
                        Log.d(TAG,"thread-${Thread.currentThread().name}:handler idle")
                        return true
                    }
                })
                Looper.loop()
                Log.d(TAG,"after looper.loop()")
            }

        }
        val thread2 = object :Runnable{
            override fun run() {
                Thread.sleep(200)
                Log.d(TAG,"thread-${Thread.currentThread().name}:send msg to thread1")
                hander.sendEmptyMessage(100)
                hander.sendMessage(Message.obtain())
            }

        }
        Thread(thread1).let {
            it.name = "1111"
            it.start()
        }
        Thread(thread2).let {
            it.name = "2222"
            it.start()
        }
        Looper.myQueue().addIdleHandler(object :MessageQueue.IdleHandler{
            override fun queueIdle(): Boolean {
                Log.d(TAG,"thread-${Thread.currentThread().name}:handler idle")
                return true
            }
        })
    }
}