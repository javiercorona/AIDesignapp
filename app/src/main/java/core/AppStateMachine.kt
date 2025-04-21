package core

object AppStateMachine {

    private var currentState: AppState = AppState.IDLE

    fun getState(): AppState = currentState

    fun setState(newState: AppState) {
        currentState = newState
    }

    fun allowedCommands(): List<String> {
        return when (currentState) {
            AppState.IDLE -> listOf("scan", "start scanning", "analyze room")
            AppState.SCANNING -> listOf("take a picture", "complete scan")
            AppState.SUGGESTING -> listOf("send me design ideas", "analyze")
            AppState.EDITING -> listOf(
                "add a couch", "place a bench", "move table", "resize table", "rotate couch", "remove item"
            )
            AppState.SHARING -> listOf("email me the design", "share design")
        }
    }


    fun isCommandValid(command: String): Boolean {
        return allowedCommands().any { command.contains(it, ignoreCase = true) }
    }
}
