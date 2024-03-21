package app.kleek.reflow.inapp.chat

import app.kleek.core.CoreHelper
import app.kleek.reflow.compat.model.PhotoModel
import app.kleek.reflow.inapp.model.toNative
import de.robv.android.xposed.XposedHelpers
import kotlinx.serialization.json.JsonObject
import org.json.JSONObject

internal class NativeChatSendingLogBuilder(
    private val type: Int,
    private val channelId: Long,
) {
    private val classLoader = CoreHelper.classLoaderGetter!!.invoke()
    private val versionConfig = CoreHelper.versionConfigGetter!!.invoke()

    private val builderClass = classLoader.loadClass(versionConfig.chatSendingLogBuilderClass)
    private val chatType = NativeChatType.toAppType(type)
    private val builder = builderClass.getConstructor(
        Long::class.java,
        classLoader.loadClass(versionConfig.chatTypeClass)
    ).newInstance(channelId, chatType)

    fun message(message: String): NativeChatSendingLogBuilder {
        builderClass.getDeclaredField(versionConfig.chatSendingLogBuilderMessageProperty)
            .set(builder, message)
        return this
    }

    fun attachment(attachment: JsonObject): NativeChatSendingLogBuilder {
        builderClass.getDeclaredField(versionConfig.chatSendingLogBuilderAttachmentProperty).set(
            builder,
            XposedHelpers.newInstance(JSONObject::class.java, attachment.toString())
        )
        return this
    }

    fun photos(photoModel: List<PhotoModel>): NativeChatSendingLogBuilder {
        val currentPhotos = builderClass.getDeclaredField(versionConfig.chatSendingLogBuilderPhotosProperty).get(builder) as ArrayList<Any>
        currentPhotos.addAll(photoModel.map { it.toNative(
            classLoader.loadClass(versionConfig.photoModelClass),
            classLoader.loadClass(versionConfig.talkPreferencesQualityDataClass),
            classLoader.loadClass(versionConfig.talkPreferencesQualityFinderClass),
            classLoader.loadClass(versionConfig.talkPreferencesQualityFinderClass).getDeclaredMethod(
                versionConfig.talkPreferencesQualityFinderMethod,
                Int::class.java
            )
        ) })
        return this
    }

    fun build(): Any {
        return builderClass.getDeclaredMethod(versionConfig.chatSendingLogBuilderBuildMethod)
            .invoke(builder)!!
    }
}