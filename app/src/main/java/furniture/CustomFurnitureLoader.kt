package furniture

import android.content.Context
import android.util.Log
import org.json.JSONObject
import java.io.InputStream

class CustomFurnitureLoader(private val context: Context) {

    fun loadFromJson(filename: String): List<FurnitureItem> {
        val items = mutableListOf<FurnitureItem>()
        try {
            val input: InputStream = context.assets.open(filename)
            val jsonString = input.bufferedReader().use { it.readText() }
            val root = JSONObject(jsonString)
            val jsonArray = root.getJSONArray("furniture")

            for (i in 0 until jsonArray.length()) {
                val obj: JSONObject = jsonArray.getJSONObject(i)

                val keywordsJson = obj.getJSONArray("keywords")
                val keywords = mutableListOf<String>()
                for (j in 0 until keywordsJson.length()) {
                    keywords.add(keywordsJson.getString(j).lowercase())
                }

                val defaultSizeArray = obj.getJSONArray("defaultSize")
                val width = defaultSizeArray.optDouble(0)
                val height = defaultSizeArray.optDouble(1)


                val item = FurnitureItem(
                    name = obj.getString("name"),
                    image = obj.getString("image"),
                    keywords = keywords,
                    width = width,
                    height = height
                )
                items.add(item)
            }

            Log.d("CustomFurnitureLoader", "Loaded furniture: $items")

        } catch (e: Exception) {
            Log.e("CustomFurnitureLoader", "Error loading furniture JSON", e)
        }
        return items
    }
}
