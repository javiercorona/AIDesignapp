package processing

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.objects.ObjectDetection
import com.google.mlkit.vision.objects.defaults.ObjectDetectorOptions

class OfflineImageProcessor(private val context: Context) {

    private val detectorOptions = ObjectDetectorOptions.Builder()
        .setDetectorMode(ObjectDetectorOptions.SINGLE_IMAGE_MODE)
        .enableMultipleObjects()
        .enableClassification()
        .build()

    private val detector = ObjectDetection.getClient(detectorOptions)

    fun analyzeImage(bitmap: Bitmap, onResult: (String) -> Unit) {
        val image = InputImage.fromBitmap(bitmap, 0)

        detector.process(image)
            .addOnSuccessListener { detectedObjects ->
                if (detectedObjects.isEmpty()) {
                    onResult("No objects detected.")
                } else {
                    val results = detectedObjects.joinToString(", ") { it.labels.firstOrNull()?.text ?: "Unknown" }
                    onResult("Detected: $results")
                }
            }
            .addOnFailureListener { e ->
                Log.e("OfflineImageProcessor", "Error analyzing image: ", e)
                onResult("Error analyzing image.")
            }
    }
}
