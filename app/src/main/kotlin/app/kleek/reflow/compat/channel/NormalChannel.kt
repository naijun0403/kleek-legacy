package app.kleek.reflow.compat.channel

import app.kleek.reflow.compat.chat.Chat
import app.kleek.reflow.packet.struct.ChatLog
import java.util.concurrent.Future

class NormalChannel(
    val commonChannel: CommonChannel
) : IChannel {

    override val channelId: Long get() =
        commonChannel.channelId

    /**
     * it is must be false, because it is normal channel
     */
    override fun isOpenChannel(): Boolean {
        return false
    }

    override fun getMembers(): List<Long> {
        return commonChannel.getMembers()
    }

    override fun getChannelName(): String {
        return commonChannel.getChannelName()
    }

    override fun getChannelType(): ChannelType {
        return commonChannel.getChannelType()
    }

    override fun sendText(message: String, noSeen: Boolean): Result<ChatLog> {
        return commonChannel.sendText(message, noSeen)
    }

    override fun send(chat: Chat, noSeen: Boolean): Result<ChatLog> {
        return commonChannel.send(chat, noSeen)
    }

}