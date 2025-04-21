package utils

import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object AutoPermissionHelper {

    private val REQUIRED_PERMISSIONS = arrayOf(
        android.Manifest.permission.RECORD_AUDIO,
        android.Manifest.permission.FOREGROUND_SERVICE,
        android.Manifest.permission.POST_NOTIFICATIONS,
        android.Manifest.permission.RECEIVE_BOOT_COMPLETED
    )

    fun hasAllPermissions(activity: Activity): Boolean {
        return REQUIRED_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(activity, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    fun requestAllPermissions(activity: Activity, requestCode: Int) {
        ActivityCompat.requestPermissions(activity, REQUIRED_PERMISSIONS, requestCode)
    }
}
