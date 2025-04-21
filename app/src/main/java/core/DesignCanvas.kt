package core

import android.graphics.PointF

data class DesignCanvas(
    val items: MutableList<FurnitureItem> = mutableListOf()
) {

    fun addItem(item: FurnitureItem) {
        items.add(item)
    }

    fun removeItemByName(name: String) {
        items.removeAll { it.name.equals(name, true) }
    }

    fun moveItem(name: String, newPosition: PointF) {
        items.find { it.name.equals(name, true) }?.position = newPosition
    }

    fun resizeItem(name: String, newSize: Pair<Float, Float>) {
        items.find { it.name.equals(name, true) }?.size = newSize
    }

    fun rotateItem(name: String, degrees: Float) {
        items.find { it.name.equals(name, true) }?.rotation = degrees
    }

    fun clearCanvas() {
        items.clear()
    }
}
