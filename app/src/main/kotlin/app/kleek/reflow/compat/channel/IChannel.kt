package app.kleek.reflow.compat.channel

import java.util.concurrent.Future

interface IChannel {

    val channelId: Long

    fun isOpenChannel(): Boolean

    fun getMembers(): List<Long>

    fun getChannelName(): String

    fun getChannelType(): ChannelType

    fun sendMessage(message: String): Future<Boolean>

}