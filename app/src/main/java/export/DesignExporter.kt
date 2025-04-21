package export

import android.content.Context
import android.graphics.*
import android.os.Environment
import core.FurnitureItem
import java.io.File
import java.io.FileOutputStream

object DesignExporter {

    fun exportFurnitureLayout(context: Context, furnitureItems: List<FurnitureItem>): File {
        val bitmap = Bitmap.createBitmap(1080, 1920, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val paint = Paint().apply {
            color = Color.BLUE
            textSize = 36f
            isAntiAlias = true
        }

        for (item in furnitureItems) {
            val x = item.position.x * 100
            val y = item.position.y * 100
            canvas.drawText(item.name, x, y, paint)
        }

        val file = File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "remodel_design.pdf")
        val output = FileOutputStream(file)

        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output)
        output.close()
        return file
    }
}
