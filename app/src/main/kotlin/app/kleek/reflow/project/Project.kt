package app.kleek.reflow.project

import app.kleek.reflow.script.client.BotClient

data class Project(
    val name: String,
    val client: BotClient
)
