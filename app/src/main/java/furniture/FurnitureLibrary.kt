
package furniture

import android.content.Context
import org.json.JSONObject

data class FurnitureItem(
    val name: String,
    val image: String,
    val keywords: List<String>,
    val width: Double,
    val height: Double
)

object FurnitureLibrary {

    private val furnitureMap = mutableMapOf<String, FurnitureItem>()

    fun load(context: Context) {
        try {
            val json = context.assets.open("custom_furniture.json")
                .bufferedReader().use { it.readText() }

            val jsonObject = JSONObject(json)
            val furnitureArray = jsonObject.getJSONArray("furniture")

            for (i in 0 until furnitureArray.length()) {
                val item = furnitureArray.getJSONObject(i)
                val name = item.getString("name")
                val image = item.getString("image")
                val keywords = item.getJSONArray("keywords")

                val defaultSize = item.getJSONObject("defaultSize")
                val width = defaultSize.getDouble("width")
                val height = defaultSize.getDouble("height")

                val keywordList = mutableListOf<String>()
                for (j in 0 until keywords.length()) {
                    keywordList.add(keywords.getString(j).lowercase())
                }

                furnitureMap[name.lowercase()] = FurnitureItem(
                    name = name,
                    image = image,
                    keywords = keywordList,
                    width = width,
                    height = height
                )
            }

            println("FurnitureLibrary loaded: ${furnitureMap.size} items.")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun findByKeyword(keyword: String): FurnitureItem? {
        return furnitureMap.values.firstOrNull {
            it.keywords.contains(keyword.lowercase())
        }
    }
}
