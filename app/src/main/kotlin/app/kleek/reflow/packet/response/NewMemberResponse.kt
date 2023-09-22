package app.kleek.reflow.packet.response

import app.kleek.expand.DefaultJson
import app.kleek.reflow.compat.loco.LocoProtocol
import app.kleek.reflow.compat.member.UserInfo
import app.kleek.reflow.packet.struct.ChatLog
import kotlinx.serialization.Serializable

@Serializable
data class NewMemberResponse(
    override val status: Int = 0,
    val chatLog: ChatLog,
) : LocoResponse {
    companion object {
        fun fromLocoPacket(packet: LocoProtocol): NewMemberResponse {
            assert(packet.header.method.name == "NEWMEM")

            return DefaultJson.decodeFromJsonElement(
                serializer(),
                packet.body.json
            )
        }
    }
}

@Serializable
data class FeedNewMemberResponse(
    val feedType: Int,
    val inviter: UserInfo? = null,
    val members: List<UserInfo>
)
