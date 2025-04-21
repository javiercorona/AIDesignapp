package furniture

import android.graphics.PointF
import android.util.Log
import core.FurnitureItem

class FurnitureManager {

    private val furnitureList = mutableListOf<FurnitureItem>()

    fun addFurniture(name: String) {
        val item = FurnitureItem(
            name = name,
            image = "placeholder.png",    // Provide a real image path or default
            keywords = listOf("default")  // Provide default or meaningful keywords
        )
        furnitureList.add(item)
        Log.d("FurnitureManager", "Added: $name")
    }

    fun moveFurniture(name: String, newX: Float, newY: Float) {
        furnitureList.find { it.name.equals(name, ignoreCase = true) }?.apply {
            position = PointF(newX, newY)
            Log.d("FurnitureManager", "$name moved to X:$newX Y:$newY")
        }
    }

    fun resizeFurniture(name: String, newSize: Pair<Float, Float>) {
        furnitureList.find { it.name.equals(name, ignoreCase = true) }?.apply {
            size = newSize
            Log.d("FurnitureManager", "$name resized to $newSize")
        }
    }

    fun rotateFurniture(name: String, degrees: Float) {
        furnitureList.find { it.name.equals(name, ignoreCase = true) }?.apply {
            rotation = degrees
            Log.d("FurnitureManager", "$name rotated to $degrees degrees")
        }
    }
}
