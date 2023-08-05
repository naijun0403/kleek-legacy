package app.kleek.reflow.config

import app.kleek.core.constant.Constant
import app.kleek.expand.DefaultToml
import com.topjohnwu.superuser.Shell
import com.topjohnwu.superuser.io.SuFile
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Config(
    @SerialName("2410230")
    val v2410230: VersionConfig,

    @SerialName("2410270")
    val v2410270: VersionConfig,

    @SerialName("2410280")
    val v2410280: VersionConfig
) {
    fun toMap(): MutableMap<Long, VersionConfig> {
        return mutableMapOf(
            2410230L to v2410230,
            2410270L to v2410270,
            2410280L to v2410280
        )
    }

    fun getByVersionCode(versionCode: Long): VersionConfig {
        return toMap()[versionCode] ?: toMap()[2410280L]!!
    }

    companion object {
        fun load(): Config {
            val file = SuFile(Constant.configPath)

            if (!file.exists()) throw IllegalStateException("Config file not found")

            val fileContent = Shell.su("cat ${file.absolutePath}").exec().out.joinToString("\n")

            return DefaultToml.decodeFromString(serializer(), fileContent)
        }
    }
}
