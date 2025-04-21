package com.example.airemodeler.voice

import android.content.Context
import android.speech.tts.TextToSpeech
import java.util.Locale

object TTSManager {
    private var tts: TextToSpeech? = null

    fun init(context: Context) {
        if (tts == null) {
            tts = TextToSpeech(context) {
                if (it == TextToSpeech.SUCCESS) {
                    tts?.language = Locale.US
                }
            }
        }
    }

    fun speak(context: Context, text: String) {
        init(context)
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    fun shutdown() {
        tts?.shutdown()
        tts = null
    }
}
