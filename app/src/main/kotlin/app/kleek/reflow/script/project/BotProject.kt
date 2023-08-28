package app.kleek.reflow.script.project

import app.kleek.reflow.script.client.BotClient

class BotProject(
    private val client: BotClient
) {
    fun getClient(): BotClient {
        return client
    }
}