package app.kleek.reflow.script.event.message

import app.kleek.reflow.compat.channel.IChannel
import app.kleek.reflow.compat.model.MessageModel
import app.kleek.reflow.script.event.Event

class MessageEvent(
    val data: MessageModel,
    val channel: IChannel
) : Event {
    override val name: String = "message"
}