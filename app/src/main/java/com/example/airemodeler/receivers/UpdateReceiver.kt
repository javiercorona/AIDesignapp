package com.example.airemodeler.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.io.File

class UpdateReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val updateFile = File(context.filesDir, "update.apk")
        if (updateFile.exists()) {
            val installIntent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(
                    androidx.core.content.FileProvider.getUriForFile(
                        context, context.packageName + ".provider", updateFile
                    ),
                    "application/vnd.android.package-archive"
                )
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION
            }
            context.startActivity(installIntent)
        }
    }
}
