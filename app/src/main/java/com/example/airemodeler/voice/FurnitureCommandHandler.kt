package com.example.airemodeler.voice


import android.content.Context
import android.graphics.PointF
import furniture.FurnitureLibrary
import furniture.FurniturePlacer


object FurnitureCommandHandler {

    fun handle(context: Context, command: String) {
        when {
            command.contains("add") || command.contains("place") -> {
                val keyword = extractKeyword(command)
                val item = FurnitureLibrary.findByKeyword(keyword)
                if (item != null) {
                    FurniturePlacer.placeItem(item, PointF(0f, 0f))
                    TTSManager.speak(context, "${item.name} has been placed at the center.")
                } else {
                    TTSManager.speak(context, "I couldn't find an item matching $keyword.")
                }
            }
            command.contains("move") -> {
                TTSManager.speak(context, "Say where you'd like the item to move.")
                // Further logic would use voice feedback or context memory.
            }
            command.contains("resize") -> {
                TTSManager.speak(context, "Please state the new size for the item.")
                // Additional logic would capture scale from user.
            }
            command.contains("rotate") -> {
                TTSManager.speak(context, "Say the rotation angle for the item.")
                // Additional logic would capture rotation.
            }
            command.contains("remove") -> {
                TTSManager.speak(context, "Tell me which item to remove.")
                // Placeholder for remove logic.
            }
            else -> {
                TTSManager.speak(context, "I'm not sure what you want to do with the furniture.")
            }
        }
    }

    private fun extractKeyword(command: String): String {
        val words = command.lowercase().split(" ")
        val knownItems = FurnitureLibrary.findByKeyword(words.last())
        return knownItems?.name ?: words.last()
    }
}
