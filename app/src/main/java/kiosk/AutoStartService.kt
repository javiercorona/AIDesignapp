package com.airemodeler.kiosk

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder

class AutoStartService : Service() {

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "AI_REMODELER_CHANNEL",
                "AI Remodeler Background",
                NotificationManager.IMPORTANCE_LOW
            )
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)

            val notification: Notification = Notification.Builder(this, "AI_REMODELER_CHANNEL")
                .setContentTitle("AI Remodeler Running")
                .setContentText("Listening for voice commands.")
                .setSmallIcon(android.R.drawable.ic_btn_speak_now)
                .build()

            startForeground(1, notification)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Here you could start the voice listener
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
