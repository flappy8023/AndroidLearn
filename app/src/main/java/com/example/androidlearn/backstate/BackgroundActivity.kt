package com.example.androidlearn.backstate

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.example.androidlearn.R
import com.example.androidlearn.ipc.IPCActivity
import com.example.androidlearn.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BackgroundActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_background)
        lifecycleScope.launch{
            delay(3000)
            Toast.makeText(this@BackgroundActivity,"我应该在后台,我要拉起activity",Toast.LENGTH_SHORT).show()
            //后台无法拉起activity,Android O增加限制？
            startActivity(Intent(this@BackgroundActivity,MainActivity::class.java))
            val channel:NotificationChannel = NotificationChannel("123","阿飞",NotificationManager.IMPORTANCE_HIGH).apply {
                description = "通知channel描述文字"
            }
            val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
            val penddingIntent = PendingIntent.getActivity(this@BackgroundActivity,0,Intent(this@BackgroundActivity,IPCActivity::class.java),PendingIntent.FLAG_IMMUTABLE)
            val notification = Notification.Builder(this@BackgroundActivity,"123")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setAutoCancel(false)
                .addAction(Notification.Action.Builder(R.drawable.ic_launcher_foreground,"添加",penddingIntent).build())
                .setContentTitle("我是title")
                .setContentText("我是内容1111111111111111111111111111烦烦烦方法")
                .build()

            manager.notify(1,notification)
        }
    }
}