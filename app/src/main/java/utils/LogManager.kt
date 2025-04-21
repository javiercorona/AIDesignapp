package utils

import android.content.Context
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey

object LogManager {
    private const val LOG_FILE = "private_logs.enc"
    private var secretKey: SecretKey = generateKey()

    private fun generateKey(): SecretKey {
        val keyGen = KeyGenerator.getInstance("AES")
        keyGen.init(256)
        return keyGen.generateKey()
    }

    fun log(context: Context, message: String) {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val iv = ByteArray(16)
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)
        val encrypted = cipher.doFinal(message.toByteArray())
        val output = context.getFileStreamPath(LOG_FILE)
        output.appendBytes(iv + encrypted)
    }

    fun readLogs(context: Context): ByteArray {
        val file = context.getFileStreamPath(LOG_FILE)
        return file.readBytes()
    }
}
