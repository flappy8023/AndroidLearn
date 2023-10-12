package com.example.androidlearn.ipc

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import com.example.androidlearn.ipc.aidl.AIDLActivity
import com.example.androidlearn.ipc.messenger.MessengerActivity
import com.example.androidlearn.ipc.provider.ProviderActivity
import com.example.androidlearn.ipc.socket.SocketActivity
import com.example.androidlearn.main.MainActivity

class IPCActivity : MainActivity() {
    override fun getData() =
        mapOf(
            "AIDL" to AIDLActivity::class.java,
            "Provider" to ProviderActivity::class.java,
            "Socket" to SocketActivity::class.java,
            "Messenger" to MessengerActivity::class.java
        )


}