package core

import android.graphics.PointF

data class FurnitureItem(
    val name: String,
    val image: String,
    val keywords: List<String>,
    var width: Double = 0.0,
    var height: Double = 0.0,
    var position: PointF = PointF(0f, 0f),
    var size: Pair<Float, Float> = Pair(1.0f, 1.0f),
    var rotation: Float = 0f
)
