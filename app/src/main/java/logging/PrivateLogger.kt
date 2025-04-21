package com.airemodeler.logging

import android.content.Context
import android.util.Base64
import java.io.File
import java.io.FileOutputStream
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

object PrivateLogger {

    private lateinit var secretKey: SecretKey
    private lateinit var iv: ByteArray

    fun initialize() {
        val keyGen = KeyGenerator.getInstance("AES")
        keyGen.init(256)
        secretKey = keyGen.generateKey()

        iv = ByteArray(16)
        java.security.SecureRandom().nextBytes(iv)
    }

    fun logToFile(context: Context, message: String) {
        if (!::secretKey.isInitialized) initialize()

        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, IvParameterSpec(iv))
        val encrypted = cipher.doFinal(message.toByteArray())

        val logFile = File(context.filesDir, "logs.enc")
        FileOutputStream(logFile, true).use { stream ->
            val encoded = Base64.encodeToString(encrypted, Base64.DEFAULT)
            stream.write((encoded + "\n").toByteArray())
        }
    }

    fun decryptLogs(context: Context): List<String> {
        val logFile = File(context.filesDir, "logs.enc")
        if (!logFile.exists()) return emptyList()

        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, secretKey, IvParameterSpec(iv))

        return logFile.readLines().mapNotNull {
            try {
                val decoded = Base64.decode(it, Base64.DEFAULT)
                cipher.doFinal(decoded).toString(Charsets.UTF_8)
            } catch (e: Exception) {
                null
            }
        }
    }
}
