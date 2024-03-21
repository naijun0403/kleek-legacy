package app.kleek.reflow.inapp.model

import android.net.Uri
import app.kleek.reflow.compat.model.PhotoModel
import java.lang.reflect.Method

fun PhotoModel.toNative(photoClass: Class<*>, qualityDataClass: Class<*>, qualityClass: Class<*>, qualityFinder: Method): Any {
    val nativeQuality = qualityFinder.invoke(null, quality.value)

    return photoClass.getConstructor(
        Uri::class.java,
        qualityDataClass,
        Boolean::class.java,
        String::class.java,
        String::class.java
    ).newInstance(
        contentUri,
        nativeQuality,
        isImageModified,
        thumbnailPath,
        comment
    )
}