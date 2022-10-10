package com.example.androidlearn.ipc

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.IBinder
import android.os.RemoteException
import android.util.Log
import com.example.androidlearn.ipc.aidl.AIDLActivity
import com.example.androidlearn.main.MainActivity

class IPCActivity : MainActivity() {
    override fun getData(): List<String> {
        return listOf("AIDL","test")
    }

    override fun click(position: Int) {
        when (position) {
            0 -> startAct(AIDLActivity::class.java)
            1-> {
                val connection: ServiceConnection = object : ServiceConnection {
                    override fun onServiceConnected(
                        componentName: ComponentName,
                        iBinder: IBinder
                    ) {
                    }

                    override fun onServiceDisconnected(componentName: ComponentName) {}
                }
                val intent = Intent().setComponent(
                    ComponentName(
                        "com.szsh.sdkc2",
                        "com.szsh.sdkc2.service.C2Service"
                    )
                )
                startService(intent)
            }
        }
    }
}