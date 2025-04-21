package state

enum class AppState {
    IDLE,
    SCANNING,
    ANALYZING,
    EDITING
}

class AppStateMachine {

    private var currentState: AppState = AppState.IDLE

    fun setState(state: AppState) {
        currentState = state
    }

    fun getState(): AppState = currentState

    fun isCommandValid(command: String): Boolean {
        return when (currentState) {
            AppState.SCANNING -> command.contains("scan", true)
            AppState.ANALYZING -> command.contains("analyze", true)
            AppState.EDITING -> listOf("move", "place", "resize", "rotate", "add", "remove").any {
                command.contains(it, true)
            }
            else -> command.contains("start", true) || command.contains("help", true)
        }
    }
}
