package com.airemodeler.kiosk

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.airemodeler.MainActivity

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            val startIntent = Intent(context, MainActivity::class.java)
            startIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                startIntent.addFlags(Intent.FLAG_ACTIVITY_RETAIN_IN_RECENTS)
            }
            context.startActivity(startIntent)
        }
    }
}
