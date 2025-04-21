package com.airemodeler.email

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.FileProvider
import java.io.File

class EmailSharing(private val context: Context) {

    fun sendDesignEmail(subject: String, body: String, attachmentPath: String) {
        val file = File(attachmentPath)

        if (!file.exists()) {
            Log.e("EmailSharing", "Attachment file not found at $attachmentPath")
            return
        }

        val uri: Uri = FileProvider.getUriForFile(
            context,
            context.packageName + ".provider",
            file
        )

        val emailIntent = Intent(Intent.ACTION_SEND).apply {
            type = "application/pdf"
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
            putExtra(Intent.EXTRA_STREAM, uri)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        context.startActivity(Intent.createChooser(emailIntent, "Send Design via Email"))
        Log.d("EmailSharing", "Email intent launched with attachment: $attachmentPath")
    }
}
