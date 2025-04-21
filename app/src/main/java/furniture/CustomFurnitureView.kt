package furniture

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CustomFurnitureView(context: Context, attrs: AttributeSet?) : View(context, attrs) {
    private val paint = Paint().apply {
        color = Color.GREEN
        style = Paint.Style.FILL
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        FurniturePlacer.listAll().forEach { item ->
            val x = item.position.x
            val y = item.position.y
            canvas.drawCircle(x, y, 20f, paint)
        }
    }
}
