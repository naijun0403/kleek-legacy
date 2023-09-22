package app.kleek.reflow.packet.struct

import app.kleek.core.CoreHelper
import app.kleek.expand.DefaultJson
import app.kleek.expand.toKotlinxSerialization
import app.kleek.reflow.logger.Logger
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.jsonPrimitive
import org.json.JSONObject

@Serializable
data class ChatLog(
    val logId: Long,
    val prevId: Long? = null,
    val chatId: Long,
    val type: Int,
    val authorId: Long,
    val message: String = "",
    val sendAt: Int,
    var attachment: JsonElement? = null,
    val msgId: Long,
    val referer: Int = 1
) {
    init {
        attachment = if (attachment != null) {
            if (attachment!! is JsonPrimitive) {
                attachment!!.jsonPrimitive.content.let {
                    if (it == "null") {
                        null
                    } else {
                        DefaultJson.decodeFromString(JsonObject.serializer(), it)
                    }
                }
            } else {
                attachment
            }
        } else {
            null
        }
    }

    companion object {
        fun fromNativeChatLog(chatLog: Any): ChatLog {
            val classLoader = chatLog.javaClass.classLoader
            val versionConfig = CoreHelper.versionConfigGetter!!.invoke()

            Logger.log("super class: ${chatLog.javaClass.superclass}")

            val chatLogClass = classLoader?.loadClass(versionConfig.chatLogClass)!!

            return ChatLog(
                logId = chatLogClass.getDeclaredField(versionConfig.chatLogLogIdField).get(chatLog) as Long,
                prevId = chatLogClass.getDeclaredField(versionConfig.chatLogPrevIdField).get(chatLog) as Long?,
                chatId = chatLogClass.getDeclaredField(versionConfig.chatLogChatIdField).get(chatLog) as Long,
                type = chatLogClass.getDeclaredField(versionConfig.chatLogTypeField).get(chatLog) as Int,
                authorId = chatLogClass.getDeclaredField(versionConfig.chatLogAuthorIdField).get(chatLog) as Long,
                message = chatLogClass.getDeclaredField(versionConfig.chatLogMessageField).get(chatLog) as String,
                sendAt = chatLogClass.getDeclaredField(versionConfig.chatLogSendAtField).get(chatLog) as Int,
                attachment = (chatLogClass.getDeclaredField(versionConfig.chatLogAttachmentField).get(chatLog) as JSONObject).toKotlinxSerialization(),
                msgId = chatLogClass.getDeclaredField(versionConfig.chatLogMessageIdField).get(chatLog) as Long,
                referer = chatLogClass.getDeclaredField(versionConfig.chatLogRefererField).get(chatLog) as Int,
            )
        }
    }
}
