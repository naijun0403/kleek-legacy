package app.kleek.core

import app.kleek.core.constant.Constant.optionPath
import app.kleek.expand.DefaultJson
import com.topjohnwu.superuser.Shell
import com.topjohnwu.superuser.io.SuFile
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.boolean
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.double
import kotlinx.serialization.json.float
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long
import kotlinx.serialization.json.put

object OptionHelper {

    const val PACKAGE_NAME = "packageName"
    const val BOT_ENABLED = "powerOn"

    fun writeOption(key: String, value: Any) {
        val file = SuFile(optionPath)
        val json = if (file.exists()) {
            Shell.cmd("cat ${file.absolutePath}").exec().out.joinToString("\n")
        } else {
            "{}"
        }
        println(json)
        val jsonElement = DefaultJson.parseToJsonElement(json)
        val jsonObject = jsonElement.jsonObject

        buildJsonObject {
            jsonObject.forEach {
                if (it.key != key) {
                    put(it.key, it.value)
                }
            }
            when (value) {
                is String -> put(key, value)
                is Int -> put(key, value)
                is Boolean -> put(key, value)
                is Float -> put(key, value)
                is Double -> put(key, value)
                is Long -> put(key, value)
                else -> throw IllegalArgumentException("Unsupported type: ${value::class.java}")
            }
        }.let {
            file.newOutputStream().write(DefaultJson.encodeToString(JsonObject.serializer(), it).toByteArray())
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> readOption(key: String, default: T): T {
        val file = SuFile(optionPath)
        val json = if (file.exists()) {
            Shell.cmd("cat ${file.absolutePath}").exec().out.joinToString("\n")
        } else {
            "{}"
        }
        println(json)
        val jsonElement = DefaultJson.parseToJsonElement(json)
        val jsonObject = jsonElement.jsonObject
        return jsonObject[key]?.let {
            when (default) {
                is String -> it.jsonPrimitive.content
                is Int -> it.jsonPrimitive.int
                is Boolean -> it.jsonPrimitive.boolean
                is Float -> it.jsonPrimitive.float
                is Double -> it.jsonPrimitive.double
                is Long -> it.jsonPrimitive.long
                else -> throw IllegalArgumentException("Unsupported type")
            } as T
        } ?: default
    }


}