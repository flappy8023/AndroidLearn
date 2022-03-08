package com.example.androidlearn.remoteviews

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RemoteViews
import androidx.annotation.RequiresApi
import com.example.androidlearn.R
import com.example.androidlearn.databinding.ActivityNotificationBinding
import com.example.androidlearn.ipc.aidl.AIDLActivity

class NotificationActivity : AppCompatActivity() {
    lateinit var binding: ActivityNotificationBinding

    @SuppressLint("RemoteViewLayout")
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNotificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val notificationManager: NotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        binding.btShow.setOnClickListener {

            notificationManager.createNotificationChannel(
                NotificationChannel(
                    "hellow",
                    "你好，世界",
                    NotificationManager.IMPORTANCE_HIGH
                )
            )
            val notification = Notification.Builder(this, "hellow")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText("contentText")
                .setContentTitle("contentTitle")
                .setContentIntent(
                    PendingIntent.getActivity(
                        this,
                        0,
                        Intent(this, AIDLActivity::class.java),
                        PendingIntent.FLAG_UPDATE_CURRENT
                    )
                )
                .build()
            notificationManager.notify(1, notification)
        }
        binding.btShowCustom.setOnClickListener {
            sendBroadcast(Intent(NewAppWidget.ACTION))
            val notification = Notification.Builder(this, "hellow")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setCustomContentView(RemoteViews(packageName, R.layout.layout_remoteview).apply {
                    setTextViewText(R.id.tv_title, "hello world")
                    setImageViewResource(R.id.iv_icon, R.drawable.ic_launcher_background)
                    setOnClickPendingIntent(
                        R.id.iv_icon,
                        PendingIntent.getActivity(
                            this@NotificationActivity,
                            0,
                            Intent(this@NotificationActivity,AIDLActivity::class.java),
                            PendingIntent.FLAG_UPDATE_CURRENT
                        )
                    )
                }).build()
            notificationManager.notify(2,notification)
        }
    }
}