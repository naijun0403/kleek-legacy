package app.kleek.reflow.compat.channel

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

    override fun sendMessage(message: String): Future<Boolean> {
        return commonChannel.sendMessage(message)
    }

}