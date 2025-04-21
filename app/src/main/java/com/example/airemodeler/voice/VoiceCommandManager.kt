package com.example.airemodeler.voice

import android.content.Context
import android.os.Bundle
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.widget.Toast


class VoiceCommandManager(private val context: Context) {

    private var speechRecognizer: SpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(context)
    private val intent = RecognizerIntent.getVoiceDetailsIntent(context).apply {
        putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
    }

    init {
        speechRecognizer.setRecognitionListener(object : RecognitionListener {
            override fun onReadyForSpeech(params: Bundle?) {}
            override fun onBeginningOfSpeech() {}
            override fun onRmsChanged(rmsdB: Float) {}
            override fun onBufferReceived(buffer: ByteArray?) {}
            override fun onEndOfSpeech() {}
            override fun onError(error: Int) {}
            override fun onResults(results: Bundle) {
                val matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
                matches?.let {
                    Toast.makeText(context, "You said: ${it[0]}", Toast.LENGTH_LONG).show()
                }
            }
            override fun onPartialResults(partialResults: Bundle?) {}
            override fun onEvent(eventType: Int, params: Bundle?) {}
        })
    }

    fun startListening() {
        speechRecognizer.startListening(intent)
    }

    fun stopListening() {
        speechRecognizer.stopListening()
    }

    fun destroy() {
        speechRecognizer.destroy()
    }
}
