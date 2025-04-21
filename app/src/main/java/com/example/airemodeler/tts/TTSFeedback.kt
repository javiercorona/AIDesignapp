package com.airemodeler.tts

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.*

class TTSFeedback(context: Context) {

    private var tts: TextToSpeech? = null

    init {
        tts = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts?.setLanguage(Locale.US)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTSFeedback", "Language not supported")
                }
            } else {
                Log.e("TTSFeedback", "Initialization failed")
            }
        }
    }

    fun speak(message: String) {
        tts?.speak(message, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
    }
}
