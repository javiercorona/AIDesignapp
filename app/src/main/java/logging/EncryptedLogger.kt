package logging

import android.content.Context
import android.util.Base64
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class EncryptedLogger(private val context: Context) {

    private val key: SecretKey by lazy {
        KeyGenerator.getInstance("AES").apply { init(256) }.generateKey()
    }

    private val iv = IvParameterSpec(ByteArray(16))

    fun log(message: String) {
        val encrypted = encrypt(message)
        val file = context.getFileStreamPath("logs.enc")
        file.appendText(encrypted + "\n")
    }

    private fun encrypt(data: String): String {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, key, iv)
        val encrypted = cipher.doFinal(data.toByteArray(Charsets.UTF_8))
        return Base64.encodeToString(encrypted, Base64.NO_WRAP)
    }

    fun clearLogs() {
        context.getFileStreamPath("logs.enc")?.delete()
    }
}
