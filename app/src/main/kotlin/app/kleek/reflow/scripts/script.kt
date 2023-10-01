package app.kleek.reflow.scripts

import app.kleek.reflow.logger.Logger
import app.kleek.reflow.project.ProjectLoader
import app.kleek.reflow.script.client.on
import app.kleek.reflow.script.event.message.MessageEvent

fun main() {
    val project = ProjectLoader.createProject("test")
    val client = project.getClient()

    client.on<MessageEvent> {
        if (data.text == "!ping") {
            channel.sendText("pong!")
        }

        if (data.text == "!channel") {
            channel.sendText(channel.getChannelName())
        }
    }
}