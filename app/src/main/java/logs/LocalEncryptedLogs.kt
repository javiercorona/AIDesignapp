package com.airemodeler.security

import android.content.Context
import android.util.Log
import java.io.File
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class LocalEncryptedLogs(private val context: Context) {

    private val key: SecretKey = generateKey()
    private val logFile = File(context.filesDir, "encrypted_logs.dat")

    private fun generateKey(): SecretKey {
        val keyGen = KeyGenerator.getInstance("AES")
        keyGen.init(256)
        return keyGen.generateKey()
    }

    fun writeLog(message: String) {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, key)
        val iv = cipher.iv
        val encryptedData = cipher.doFinal(message.toByteArray())
        logFile.appendBytes(iv + encryptedData)
        Log.d("LocalEncryptedLogs", "Log written securely.")
    }

    fun readLogs(): List<String> {
        if (!logFile.exists()) return emptyList()

        val logs = mutableListOf<String>()
        val data = logFile.readBytes()
        var index = 0

        while (index < data.size) {
            val iv = data.copyOfRange(index, index + 16)
            index += 16
            val cipherTextEnd = index + 128 // rough read size assumption
            if (cipherTextEnd > data.size) break

            val cipherText = data.copyOfRange(index, cipherTextEnd)
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            cipher.init(Cipher.DECRYPT_MODE, key, IvParameterSpec(iv))
            logs.add(String(cipher.doFinal(cipherText)))
            index = cipherTextEnd
        }

        return logs
    }
}
