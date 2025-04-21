package library

import android.content.Context
import android.graphics.PointF
import org.json.JSONArray
import org.json.JSONObject
import java.io.InputStream
import core.FurnitureItem


class CustomFurnitureLoader(private val context: Context) {

    fun loadFromJson(filename: String): List<FurnitureItem> {
        val items = mutableListOf<FurnitureItem>()
        try {
            val input: InputStream = context.assets.open(filename)
            val jsonString = input.bufferedReader().use { it.readText() }
            val jsonObject = JSONObject(jsonString)
            val jsonArray: JSONArray = jsonObject.getJSONArray("furniture")

            for (i in 0 until jsonArray.length()) {
                val obj: JSONObject = jsonArray.getJSONObject(i)

                // Parse size
                val defaultSizeArray = obj.getJSONArray("defaultSize")
                val width = defaultSizeArray.optDouble(0, 0.0)
                val height = defaultSizeArray.optDouble(1, 0.0)

                // Create the item fully initialized
                val item = core.FurnitureItem(
                    name = obj.getString("name"),
                    image = obj.getString("image"),
                    keywords = obj.getJSONArray("keywords").let { array ->
                        List(array.length()) { index -> array.getString(index) }
                    },
                    width = width,
                    height = height,
                    position = PointF(0f, 0f),
                    size = Pair(width.toFloat(), height.toFloat()),
                    rotation = 0f
                )

                items.add(item)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return items
    }
}
