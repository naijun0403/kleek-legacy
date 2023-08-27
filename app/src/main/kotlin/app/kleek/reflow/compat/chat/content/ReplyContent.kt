package app.kleek.reflow.compat.chat.content

import app.kleek.reflow.compat.chat.model.ReplyModel
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

class ReplyContent(
    private val model: ReplyModel
): Content {

    override fun append(data: MutableMap<String, JsonElement>, extra: JsonObject) {
        data["src_logId"] = JsonPrimitive(model.logId)
        data["src_userId"] = JsonPrimitive(model.userId)
        data["src_linkId"] = JsonPrimitive(model.linkId)
        data["src_type"] = JsonPrimitive(model.type)
        data["src_message"] = JsonPrimitive(model.message)
    }

}
