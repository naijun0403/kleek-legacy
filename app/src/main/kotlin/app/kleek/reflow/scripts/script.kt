package app.kleek.reflow.scripts

import androidx.core.net.toUri
import app.kleek.reflow.compat.chat.Chat
import app.kleek.reflow.compat.chat.ChatType
import app.kleek.reflow.compat.model.PhotoModel
import app.kleek.reflow.logger.Logger
import app.kleek.reflow.project.ProjectLoader
import app.kleek.reflow.script.client.on
import app.kleek.reflow.script.event.message.MessageEvent
import java.io.File

fun main() {
    val project = ProjectLoader.createProject("test")
    val client = project.getClient()

    client.on<MessageEvent> {
        if (data.text == "!ping") {
            channel.sendText("pong!").onFailure {
                Logger.log(it.stackTraceToString())
            }
        }

        if (data.text == "!channel") {
            channel.sendText(channel.getChannelName())
        }

        if (data.text == "!photo") {
            channel.send(
                Chat(
                    type = ChatType.PHOTO,
                    text = "",
                    photos = listOf(
                        PhotoModel(
                            File("/storage/emulated/0/DCIM/Camera/2024-03-22-02-00-45-088.jpg").toUri()
                        )
                    )
                )
            ).onFailure {
                Logger.log(it.stackTraceToString())
            }
        }
    }
}