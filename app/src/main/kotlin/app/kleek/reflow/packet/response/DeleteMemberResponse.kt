package app.kleek.reflow.packet.response

import app.kleek.expand.DefaultJson
import app.kleek.reflow.compat.loco.LocoProtocol
import app.kleek.reflow.compat.member.UserInfo
import app.kleek.reflow.packet.struct.ChatLog
import kotlinx.serialization.Serializable

@Serializable
data class DeleteMemberResponse(
    override val status: Int = 0,
    val chatLog: ChatLog,
) : LocoResponse {
    companion object {
        fun fromLocoPacket(packet: LocoProtocol): DeleteMemberResponse {
            assert(packet.header.method.name == "DELMEM")

            return DefaultJson.decodeFromJsonElement(
                serializer(),
                packet.body.json
            )
        }
    }
}

@Serializable
data class FeedDeleteMemberResponse(
    val feedType: Int,
    val hidden: Boolean,
    val member: UserInfo
)