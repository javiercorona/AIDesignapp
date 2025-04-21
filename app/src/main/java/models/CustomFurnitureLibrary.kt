package com.airemodeler.models

import android.content.Context
import org.json.JSONArray
import java.io.InputStream

data class FurnitureItem(
    val name: String,
    val width: Float,
    val height: Float,
    val imagePath: String
)

class CustomFurnitureLibrary {

    val furnitureItems = mutableListOf<FurnitureItem>()

    fun loadFromJson(context: Context, filename: String) {
        try {
            val inputStream: InputStream = context.assets.open(filename)
            val jsonString = inputStream.bufferedReader().use { it.readText() }
            val jsonArray = JSONArray(jsonString)

            furnitureItems.clear()
            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                furnitureItems.add(
                    FurnitureItem(
                        obj.getString("name"),
                        obj.getDouble("width").toFloat(),
                        obj.getDouble("height").toFloat(),
                        obj.getString("imagePath")
                    )
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
