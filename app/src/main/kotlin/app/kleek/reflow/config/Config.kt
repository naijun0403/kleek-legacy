package app.kleek.reflow.config

import app.kleek.core.constant.Constant
import app.kleek.expand.DefaultToml
import com.topjohnwu.superuser.Shell
import com.topjohnwu.superuser.io.SuFile
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Config(
    @SerialName("2410280")
    val v2410280: VersionConfig,

    @SerialName("2610360")
    val v2610360: VersionConfig,

    @SerialName("2610370")
    val v2610370: VersionConfig
) {
    fun toMap(): MutableMap<Long, VersionConfig> {
        return mutableMapOf(
            2410280L to v2410280,
            2610360L to v2610360,
            2610370L to v2610370
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
