package app.kleek.reflow.compat.channel

import app.kleek.reflow.inapp.channel.NativeChannel

class Channel(
    private val nativeChannel: NativeChannel,
) {

    val channelId: Long get() =
        nativeChannel.chatRoomClass.getDeclaredField("c").getLong(nativeChannel.channel)

}