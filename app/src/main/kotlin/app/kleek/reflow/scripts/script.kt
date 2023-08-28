package app.kleek.reflow.scripts

import app.kleek.reflow.project.ProjectLoader
import app.kleek.reflow.script.client.on
import app.kleek.reflow.script.event.MessageEvent

fun main() {
    val project = ProjectLoader.createProject("test")
    val client = project.getClient()

    client.on<MessageEvent> {
        if (data.text == "!ping") {
            channel.sendText("pong!")
        }
    }
}