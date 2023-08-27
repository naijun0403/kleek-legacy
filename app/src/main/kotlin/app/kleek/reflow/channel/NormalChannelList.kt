package app.kleek.reflow.channel

import app.kleek.reflow.compat.channel.NormalChannel

class NormalChannelList {

    private val map = mutableMapOf<Long, NormalChannel>()

    fun put(channel: NormalChannel) {
        map[channel.channelId] = channel
    }

    fun get(channelId: Long): NormalChannel? {
        return map[channelId]
    }

}