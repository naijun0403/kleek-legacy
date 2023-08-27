package app.kleek.reflow.scripts

import app.kleek.reflow.channel.ChannelListManager
import app.kleek.reflow.compat.channel.IChannel
import app.kleek.reflow.compat.model.MessageModel
import kotlinx.serialization.json.JsonElement

fun onResponse(data: MessageModel, channel: IChannel) {

    if (data.text == "!ping") {
        channel.sendText("pong!")
    }

}

fun onPacket(method: String, data: JsonElement, channelListManager: ChannelListManager) {

}