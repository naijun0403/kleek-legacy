package app.kleek.reflow.inapp.chat

import app.kleek.core.CoreHelper
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

    fun build(): Any {
        return builderClass.getDeclaredMethod(versionConfig.chatSendingLogBuilderBuildMethod)
            .invoke(builder)!!
    }
}