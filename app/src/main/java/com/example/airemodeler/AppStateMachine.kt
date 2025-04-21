package com.example.airemodeler

class AppStateMachine {

    private var currentState: State = State.IDLE

    fun setState(newState: State) {
        currentState = newState
    }

    fun isListening(): Boolean = currentState == State.LISTENING
    fun isIdle(): Boolean = currentState == State.IDLE
    fun isProcessing(): Boolean = currentState == State.PROCESSING
    fun isSpeaking(): Boolean = currentState == State.SPEAKING
}
