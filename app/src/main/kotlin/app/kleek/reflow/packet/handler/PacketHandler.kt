package app.kleek.reflow.packet.handler

import app.kleek.reflow.channel.ChannelListManager
import app.kleek.reflow.compat.channel.CommonChannel
import app.kleek.reflow.compat.channel.NormalChannel
import app.kleek.reflow.compat.channel.OpenChannel
import app.kleek.reflow.compat.loco.LocoProtocol
import app.kleek.reflow.compat.model.MessageModel
import app.kleek.reflow.inapp.channel.NativeChannelFinder
import app.kleek.reflow.packet.response.MessageResponse
import app.kleek.reflow.scripts.onPacket
import app.kleek.reflow.scripts.onResponse

class PacketHandler {
    val channelListManager = ChannelListManager()

    fun handle(packet: LocoProtocol) {
        when (packet.header.method.name) {
            "MSG" -> {
                val msg = MessageResponse.fromLocoPacket(packet)
                val channel = channelListManager.get(msg.chatLog.chatId) ?: let {
                    val commonChannel = CommonChannel(
                        NativeChannelFinder.findChannel(msg.chatLog.chatId) ?: throw Exception("Channel not found")
                    )

                    if (msg.li != null) {
                        // open channel
                        channelListManager.openChannelList.put(
                            OpenChannel(
                                commonChannel
                            )
                        )
                    } else {
                        // normal channel
                        channelListManager.normalChannelList.put(
                            NormalChannel(
                                commonChannel
                            )
                        )
                    }

                    channelListManager.get(msg.chatLog.chatId) ?: throw Exception("Channel not found")
                }

                val messageModel = MessageModel.fromChatLog(msg.chatLog)

                // TODO: client event emitter
                onResponse(messageModel, channel)
                onPacket(packet.header.method.name, packet.body.json, channelListManager)
            }

            else -> {
                onPacket(packet.header.method.name, packet.body.json, channelListManager)
            }
        }
    }
}