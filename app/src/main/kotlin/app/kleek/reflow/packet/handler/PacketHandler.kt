package app.kleek.reflow.packet.handler

import app.kleek.expand.DefaultJson
import app.kleek.reflow.channel.ChannelListManager
import app.kleek.reflow.compat.channel.CommonChannel
import app.kleek.reflow.compat.channel.NormalChannel
import app.kleek.reflow.compat.channel.OpenChannel
import app.kleek.reflow.compat.loco.LocoProtocol
import app.kleek.reflow.compat.model.MessageModel
import app.kleek.reflow.inapp.channel.NativeChannelFinder
import app.kleek.reflow.packet.response.DeleteMemberResponse
import app.kleek.reflow.packet.response.FeedDeleteMemberResponse
import app.kleek.reflow.packet.response.FeedNewMemberResponse
import app.kleek.reflow.packet.response.MessageResponse
import app.kleek.reflow.packet.response.NewMemberResponse
import app.kleek.reflow.script.bot.Bot
import app.kleek.reflow.script.event.message.MessageEvent
import app.kleek.reflow.script.event.user.UserJoinEvent
import app.kleek.reflow.script.event.user.UserQuitEvent
import app.kleek.reflow.scripts.main

class PacketHandler {
    val channelListManager = ChannelListManager()

//    init {
//        main() // TODO: remove this (temporary)
//    }

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

                Bot.sendEvent(
                    MessageEvent(
                        messageModel,
                        channel
                    )
                )
            }

            "NEWMEM" -> {
                val response = NewMemberResponse.fromLocoPacket(packet)
                val feedContent = DefaultJson.decodeFromString<FeedNewMemberResponse>(
                    response.chatLog.message
                )

                val channel = channelListManager.get(response.chatLog.chatId) ?: let {
                    val commonChannel = CommonChannel(
                        NativeChannelFinder.findChannel(response.chatLog.chatId) ?: throw Exception("Channel not found")
                    )

                    if (feedContent.feedType != 1) {
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

                    channelListManager.get(response.chatLog.chatId) ?: throw Exception("Channel not found")
                }

                Bot.sendEvent(
                    UserJoinEvent(
                        channel,
                        feedContent.members,
                        feedContent.inviter
                    )
                )
            }

            "DELMEM" -> {
                val response = DeleteMemberResponse.fromLocoPacket(packet)
                val feedContent = DefaultJson.decodeFromString<FeedDeleteMemberResponse>(
                    response.chatLog.message
                )

                val channel = channelListManager.get(response.chatLog.chatId) ?: let {
                    val commonChannel = CommonChannel(
                        NativeChannelFinder.findChannel(response.chatLog.chatId) ?: throw Exception("Channel not found")
                    )

                    if (commonChannel.isOpenChannel()) {
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

                    channelListManager.get(response.chatLog.chatId) ?: throw Exception("Channel not found")
                }

                Bot.sendEvent(
                    UserQuitEvent(
                        channel,
                        feedContent.member,
                        feedContent.hidden
                    )
                )
            }

            else -> {
                // TODO: handle other packets
            }
        }
    }
}