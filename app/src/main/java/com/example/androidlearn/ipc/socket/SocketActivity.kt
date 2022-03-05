package com.example.androidlearn.ipc.socket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.example.androidlearn.R
import java.io.*
import java.net.Socket

class SocketActivity : AppCompatActivity() {
    private var socketClient: Socket? = null
    private var printer: PrintWriter? = null

    companion object {
        const val MSG_CONNECTED = 11
        const val MSG_RECEIVED = 12
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_socket)
        startService(Intent(this, TcpServerService::class.java))
        Thread {
            connectTcpServer()
        }.start()
        findViewById<Button>(R.id.send).setOnClickListener {
            Thread {
                if (null != printer) {
                    printer?.println("nice to meet you")
                }
            }.start()
        }
    }

    private val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                MSG_CONNECTED -> {
                    findViewById<Button>(R.id.send).isEnabled = true
                }
                MSG_RECEIVED -> {
                    findViewById<TextView>(R.id.tv_msg).text =
                        findViewById<TextView>(R.id.tv_msg).text.toString() + "\n" + msg.obj
                }
            }
        }
    }

    private fun connectTcpServer() {
        var socket: Socket? = null
        while (socket == null) {
            try {


                socket = Socket("localhost", 8282)
                socketClient = socket
                printer =
                    PrintWriter(BufferedWriter(OutputStreamWriter(socket?.getOutputStream())), true)
                handler.sendEmptyMessage(MSG_CONNECTED)
            } catch (e: IOException) {
                Thread.sleep(1000)
            }
        }

        val bufferReader = BufferedReader(InputStreamReader(socket?.getInputStream()))
        while (!isFinishing) {
            val msg = bufferReader.readLine()
            if (null != msg) {
                handler.obtainMessage(MSG_RECEIVED, msg).sendToTarget()
            }
        }
        printer?.close()
        bufferReader.close()
        socket?.close()

    }

    override fun onDestroy() {
        super.onDestroy()
        socketClient?.shutdownInput()
        socketClient?.close()
    }

}