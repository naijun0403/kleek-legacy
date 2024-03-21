package app.kleek.reflow.compat.model

import android.net.Uri
import androidx.core.net.toFile

data class PhotoModel(
    val contentUri: Uri,
    val quality: PhotoQuality = PhotoQuality.ORIGINAL,
    val isImageModified: Boolean = false,
    val thumbnailPath: String = contentUri.toFile().absolutePath,
    val comment: String = ""
)

enum class PhotoQuality(val value: Int) {
    UNKNOWN(-1),
    LOW(0),
    HIGH(1),
    ORIGINAL(2)
}
