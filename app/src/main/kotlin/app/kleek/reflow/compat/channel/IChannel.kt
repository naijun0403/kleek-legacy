package app.kleek.reflow.compat.channel

import app.kleek.reflow.compat.chat.Chat
import app.kleek.reflow.packet.struct.ChatLog
import java.util.concurrent.Future

interface IChannel {

    val channelId: Long

    fun isOpenChannel(): Boolean

    fun getMembers(): List<Long>

    fun getChannelName(): String

    fun getChannelType(): ChannelType

    fun sendText(message: String, noSeen: Boolean = false): Result<ChatLog>

    fun send(chat: Chat, noSeen: Boolean = false): Result<ChatLog>

}