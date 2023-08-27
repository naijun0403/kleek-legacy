package app.kleek.reflow.packet.response

import app.kleek.expand.DefaultJson
import app.kleek.reflow.compat.loco.LocoProtocol
import app.kleek.reflow.packet.struct.ChatLog
import kotlinx.serialization.Serializable

@Serializable
data class MessageResponse(
    override val status: Int = 0,
    val errMsg: String? = null,
    val chatId: Long,
    val chatLog: ChatLog,
    val logId: Long = chatLog.logId,
    val noSeen: Boolean,
    val authorNickname: String? = null, // only in openchat
    val li: Long? = null, // only in openchat
    val pushAlert: Boolean = false,
    val notiRead: Boolean? = null
) : LocoResponse {
    companion object {
        fun fromLocoPacket(packet: LocoProtocol): MessageResponse {
            assert(packet.header.method.name == "MSG")

            return DefaultJson.decodeFromJsonElement(
                serializer(),
                packet.body.json
            )
        }
    }
}
