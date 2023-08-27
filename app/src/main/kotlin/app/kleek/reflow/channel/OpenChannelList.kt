package app.kleek.reflow.channel

import app.kleek.reflow.compat.channel.OpenChannel

class OpenChannelList {

    private val map = mutableMapOf<Long, OpenChannel>()

    fun put(channel: OpenChannel) {
        map[channel.channelId] = channel
    }

    fun get(channelId: Long): OpenChannel? {
        return map[channelId]
    }

}