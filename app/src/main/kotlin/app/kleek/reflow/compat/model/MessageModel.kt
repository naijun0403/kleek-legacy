package app.kleek.reflow.compat.model

import app.kleek.reflow.packet.struct.ChatLog
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class MessageModel(
    val logId: Long,
    val text: String,
    val senderId: Long,
    val type: Int,
    val sendAt: Int,
    val attachment: JsonElement? = null,
    val msgId: Long,
    val chatLog: ChatLog,
) {
    companion object {
        fun fromChatLog(chatLog: ChatLog): MessageModel {
            return MessageModel(
                chatLog.logId,
                chatLog.message,
                chatLog.authorId,
                chatLog.type,
                chatLog.sendAt,
                chatLog.attachment,
                chatLog.msgId,
                chatLog
            )
        }
    }
}
