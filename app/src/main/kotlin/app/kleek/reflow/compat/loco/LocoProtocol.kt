package app.kleek.reflow.compat.loco

import app.kleek.core.CoreHelper
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import org.json.JSONObject

data class LocoProtocol(
    val header: LocoHeader,
    val body: LocoBody
) {
    companion object {
        fun fromLoaded(classLoader: ClassLoader, load: Any): LocoProtocol {
            val versionConfig = CoreHelper.versionConfigGetter!!()

            val protocolClass = classLoader.loadClass(versionConfig.locoProtocolClass)
            val headerClass = classLoader.loadClass(versionConfig.locoHeaderClass)
            val bodyClass = classLoader.loadClass(versionConfig.locoBodyClass)
            val methodClass = classLoader.loadClass(versionConfig.locoMethodClass)

            val header = protocolClass.getDeclaredField(versionConfig.locoGetHeaderField).get(load)
            val body = protocolClass.getDeclaredField(versionConfig.locoGetBodyField).get(load)

            val packetId = headerClass.getDeclaredField(versionConfig.locoPacketIdField).get(header) as Int
            val status = headerClass.getDeclaredField(versionConfig.locoStatusField).get(header) as Short
            val method = headerClass.getDeclaredField(versionConfig.locoMethodField).get(header)

            val methodBytes =
                methodClass.getDeclaredMethod(versionConfig.locoMethodBytesMethod).invoke(method) as ByteArray
            val methodName = methodClass.getDeclaredMethod(versionConfig.locoMethodNameMethod).invoke(method) as String

            val bodyMap = bodyClass.getDeclaredMethod(versionConfig.locoBodyToMap).invoke(body) as Map<*, *>
            val bodyJson = JSONObject(bodyMap)

            return LocoProtocol(
                header = LocoHeader(
                    packetId = packetId,
                    status = status,
                    method = LocoMethod(
                        methodBytes,
                        methodName
                    )
                ),
                body = LocoBody(
                    Json.decodeFromString<JsonElement>(bodyJson.toString())
                )
            )
        }
    }
}
