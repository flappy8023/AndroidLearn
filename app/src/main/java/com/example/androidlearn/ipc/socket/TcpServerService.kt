package com.example.androidlearn.ipc.socket

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.*
import java.net.ServerSocket
import java.util.*

class TcpServerService : Service() {
    private var isDestoryed = false
    private val msgs = arrayOf("你好啊", "很高兴见到你", "中午吃啥", "下班那啦")
    override fun onCreate() {
        super.onCreate()
        initServer()
    }

    private fun initServer() {
        Thread() {
            val serverSocket = ServerSocket(8282)
            val client = serverSocket.accept()
            Log.d("fff","accept client")
            Thread(){
                //用于读取客户端消息
                val reader = BufferedReader(InputStreamReader(client.getInputStream()))
                //用于回应客户端消息
                val writer = PrintWriter(BufferedWriter(OutputStreamWriter(client.getOutputStream())),true)
                while (!isDestoryed){
                    val str = reader.readLine()
                    //客户端断开
                    if (null==str){
                        break
                    }
                    Log.d("fff","receive msg from client,msg = $str")
                    val responseMsg = msgs.get(Random().nextInt(msgs.size))
                    writer.println(responseMsg)

                }
                writer.close()
                reader.close()
                client.close()
            }.start()
        }.start()

    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        isDestoryed = true
    }
}