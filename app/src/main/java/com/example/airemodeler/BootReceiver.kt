package com.example.airemodeler

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        if (Intent.ACTION_BOOT_COMPLETED == intent?.action) {
            Log.d("BootReceiver", "Device rebooted, starting MainVoiceService.")

            val serviceIntent = Intent(context, MainVoiceService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(serviceIntent)
            } else {
                context.startService(serviceIntent)
            }
        }
    }
}
