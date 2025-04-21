package core

import android.content.Context
import com.example.airemodeler.voice.TTSManager

object VoiceCommandRouter {

    fun handleCommand(context: Context, command: String) {
        if (!AppStateMachine.isCommandValid(command)) {
            TTSManager.speak(context, "Sorry, this command is not valid right now.")
            return
        }

        when {
            command.contains("scan", true) -> {
                AppStateMachine.setState(AppState.SCANNING)
                TTSManager.speak(context, "Starting room scan.")
                // Trigger scanning logic here.
            }
            command.contains("design", true) || command.contains("suggestions", true) -> {
                AppStateMachine.setState(AppState.SUGGESTING)
                TTSManager.speak(context, "Analyzing and preparing suggestions.")
                // Trigger AI suggestions here.
            }
            command.contains("place", true) || command.contains("move", true) -> {
                AppStateMachine.setState(AppState.EDITING)
                TTSManager.speak(context, "Editing your design.")
                // Handle furniture placement here.
            }
            command.contains("email", true) || command.contains("share", true) -> {
                AppStateMachine.setState(AppState.SHARING)
                TTSManager.speak(context, "Preparing to send your design by email.")
                // Trigger EmailHelper here.
            }
            else -> TTSManager.speak(context, "Command not recognized.")
        }
    }
}
