package app.kleek.reflow.compat.chat

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject

data class Chat(
    val type: Int,
    val text: String,
    val attachment: JsonObject = buildJsonObject { },
)
