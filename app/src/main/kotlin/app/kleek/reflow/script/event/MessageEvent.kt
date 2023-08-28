package app.kleek.reflow.script.event

import app.kleek.reflow.compat.channel.IChannel
import app.kleek.reflow.compat.model.MessageModel

class MessageEvent(
    val data: MessageModel,
    val channel: IChannel
) : Event {
    override val name: String = "message"
}