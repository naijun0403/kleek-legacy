package app.kleek.reflow.compat.chat

import app.kleek.expand.DefaultJson
import app.kleek.reflow.compat.chat.content.Content
import app.kleek.reflow.compat.model.PhotoModel
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.jsonObject

data class Chat(
    val type: Int,
    val text: String,
    val attachment: JsonObject = buildJsonObject { },
    val photos: List<PhotoModel> = listOf()
) {

    constructor(
        type: ChatType,
        text: String,
        attachment: JsonObject = buildJsonObject { },
        photos: List<PhotoModel> = listOf()
    ) : this(type.type, text, attachment, photos)

    companion object {
        fun newBuilder(): Builder {
            return Builder()
        }
    }

    class Builder {

        private var message = ""
        private var attachment = mutableMapOf<String, JsonElement>()
        private var supplement = mutableMapOf<String, JsonElement>()

        fun message(message: String): Builder {
            this.message += message
            return this
        }

        fun attachment(content: Content): Builder {
            val extra = mutableMapOf<String, JsonElement>()

            extra["message"] = JsonPrimitive(message)

            content.append(attachment, JsonObject(extra))
            return this
        }

        fun supplement(content: Content): Builder {
            content.append(supplement, buildJsonObject {  })
            return this
        }

        fun build(type: ChatType): Chat {
            return build(type.type)
        }

        fun build(type: Int): Chat {
            return Chat(
                type = type,
                text = message,
                attachment = DefaultJson.encodeToJsonElement(
                    MapSerializer(
                        String.serializer(),
                        JsonElement.serializer()
                    ),
                    attachment
                ).jsonObject
            )
        }

    }

}
