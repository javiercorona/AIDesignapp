package com.example.airemodeler

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat


class MainVoiceService : Service() {

    override fun onCreate() {
        super.onCreate()
        startForegroundService()
        Log.d("MainVoiceService", "Foreground Voice Service started")
        // TODO: Add voice recognition + response logic here
    }

    private fun startForegroundService() {
        val channelId = "voice_service_channel"
        val channelName = "AI Remodeler Voice Service"

        val manager = getSystemService(NotificationManager::class.java)
        if (manager.getNotificationChannel(channelId) == null) {
            val chan = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_LOW)
            manager.createNotificationChannel(chan)
        }

        val notification = NotificationCompat.Builder(this, channelId)
            .setContentTitle("AI Remodeler")
            .setContentText("Listening for voice commands…")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setOngoing(true)
            .build()

        startForeground(1, notification)
    }




    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // You can restart speech recognition here if needed
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
