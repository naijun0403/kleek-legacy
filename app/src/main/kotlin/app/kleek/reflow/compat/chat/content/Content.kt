package app.kleek.reflow.compat.chat.content

import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject

interface Content {
    fun append(data: MutableMap<String, JsonElement>, extra: JsonObject)
}
