package com.example.airemodeler

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.Locale

class TextToSpeechHelper(context: Context) {

    private var tts: TextToSpeech? = null

    init {
        tts = TextToSpeech(context.applicationContext) { status ->
            if (status == TextToSpeech.SUCCESS) {
                val result = tts?.setLanguage(Locale.US)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Log.e("TTS", "Language not supported.")
                } else {
                    Log.i("TTS", "TextToSpeech initialized.")
                }
            } else {
                Log.e("TTS", "Initialization failed.")
            }
        }
    }

    fun speak(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null, "AI_REMODELER_TTS")
    }

    fun shutdown() {
        tts?.stop()
        tts?.shutdown()
    }
}
