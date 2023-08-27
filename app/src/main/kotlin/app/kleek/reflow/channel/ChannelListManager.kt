package app.kleek.reflow.channel

import app.kleek.reflow.compat.channel.IChannel

class ChannelListManager  {

    val normalChannelList = NormalChannelList()
    val openChannelList = OpenChannelList()

    fun get(channelId: Long): IChannel? {
        return normalChannelList.get(channelId) ?: openChannelList.get(channelId)
    }

}