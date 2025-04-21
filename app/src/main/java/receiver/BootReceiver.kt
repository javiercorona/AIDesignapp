package receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.airemodeler.MainVoiceService

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            Log.d("BootReceiver", "Device booted, starting service.")
            val serviceIntent = Intent(context, MainVoiceService::class.java)
            context.startForegroundService(serviceIntent)
        }
    }
}
