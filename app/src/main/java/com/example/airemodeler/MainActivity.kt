package com.example.airemodeler

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val requestCode = 1001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!PermissionHelper.hasAllPermissions(this)) {
            PermissionHelper.requestAllPermissions(this, requestCode)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == this.requestCode) {
            if (PermissionHelper.hasAllPermissions(this)) {
                Toast.makeText(this, "All permissions granted.", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Permissions denied.", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }
}
