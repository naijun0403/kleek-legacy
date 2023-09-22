package app.kleek.reflow.compat.channel

import app.kleek.core.CoreHelper
import app.kleek.reflow.compat.chat.Chat
import app.kleek.reflow.packet.struct.ChatLog
import java.util.concurrent.Future

class OpenChannel(
    val commonChannel: CommonChannel
) : IChannel {

    override val channelId: Long
        get() = commonChannel.channelId

    val linkId: Long
        get() {
            val versionConfig = CoreHelper.versionConfigGetter?.invoke() ?: return -1

            commonChannel.nativeChannel.chatRoomClass.getDeclaredField(versionConfig.chatRoomLinkIdField).apply {
                isAccessible = true
                return get(commonChannel.nativeChannel.channel) as Long
            }
        }

    /**
     * it is must be true, because it is open channel
     */
    override fun isOpenChannel(): Boolean {
        return true
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