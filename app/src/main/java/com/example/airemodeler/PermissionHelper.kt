package com.example.airemodeler

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat




object PermissionHelper {

    private val requiredPermissions = arrayOf(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.POST_NOTIFICATIONS,
        Manifest.permission.READ_MEDIA_IMAGES
    )

    fun hasAllPermissions(activity: Activity): Boolean {
        return requiredPermissions.all {
            ContextCompat.checkSelfPermission(activity, it) == PackageManager.PERMISSION_GRANTED
        }
    }

    fun requestAllPermissions(activity: Activity, requestCode: Int) {
        ActivityCompat.requestPermissions(
            activity,
            requiredPermissions,
            requestCode
        )
    }
}
