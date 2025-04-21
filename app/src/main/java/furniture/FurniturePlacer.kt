package furniture

import android.graphics.PointF

data class PlacedFurniture(
    val item: FurnitureItem,
    var position: PointF,
    var rotation: Float = 0f,
    var scale: Float = 1.0f
)

object FurniturePlacer {
    private val placedItems = mutableListOf<PlacedFurniture>()

    fun placeItem(item: FurnitureItem, position: PointF): PlacedFurniture {
        val placed = PlacedFurniture(item, position)
        placedItems.add(placed)
        return placed
    }

    fun moveItem(item: PlacedFurniture, newPosition: PointF) {
        item.position = newPosition
    }

    fun rotateItem(item: PlacedFurniture, angle: Float) {
        item.rotation = angle
    }

    fun resizeItem(item: PlacedFurniture, scale: Float) {
        item.scale = scale
    }

    fun removeItem(item: PlacedFurniture) {
        placedItems.remove(item)
    }

    fun listAll(): List<PlacedFurniture> = placedItems
}
