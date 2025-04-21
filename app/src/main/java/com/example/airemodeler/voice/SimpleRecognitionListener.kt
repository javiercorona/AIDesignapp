package com.example.airemodeler.voice

import android.speech.RecognitionListener
import android.speech.SpeechRecognizer
import android.os.Bundle
import android.util.Log

class SimpleRecognitionListener(private val onResult: (String) -> Unit) : RecognitionListener {

    override fun onReadyForSpeech(params: Bundle?) {}
    override fun onBeginningOfSpeech() {}
    override fun onRmsChanged(rmsdB: Float) {}
    override fun onBufferReceived(buffer: ByteArray?) {}
    override fun onEndOfSpeech() {}

    override fun onError(error: Int) {
        Log.e("VoiceRecognition", "Error Code: $error")
    }

    override fun onResults(results: Bundle?) {
        val matches = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
        matches?.firstOrNull()?.let {
            onResult(it)
        }
    }

    override fun onPartialResults(partialResults: Bundle?) {}
    override fun onEvent(eventType: Int, params: Bundle?) {}
}
