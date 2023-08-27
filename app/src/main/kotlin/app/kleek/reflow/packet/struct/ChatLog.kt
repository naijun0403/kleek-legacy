package app.kleek.reflow.packet.struct

import app.kleek.expand.DefaultJson
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

@Serializable
data class ChatLog(
    val logId: Long,
    val prevId: Long? = null,
    val chatId: Long,
    val type: Int,
    val authorId: Long,
    val message: String = "",
    val sendAt: Long,
    var attachment: JsonElement? = null,
    val msgId: Long,
) {
    init {
        attachment = if (attachment != null) {
            attachment!!.jsonPrimitive.content.let {
                if (it == "null") {
                    null
                } else {
                    DefaultJson.decodeFromString(JsonObject.serializer(), it)
                }
            }
        } else {
            null
        }
    }
}
