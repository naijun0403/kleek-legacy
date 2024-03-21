package app.kleek.reflow.config

import app.kleek.core.constant.Constant
import app.kleek.expand.DefaultToml
import com.topjohnwu.superuser.Shell
import com.topjohnwu.superuser.io.SuFile
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Config(
    @SerialName("2610610")
    val v2610610: VersionConfig
) {
    fun toMap(): MutableMap<Long, VersionConfig> {
        return mutableMapOf(
            2610610L to v2610610
        )
    }

    fun getByVersionCode(versionCode: Long): VersionConfig {
        return toMap()[versionCode] ?: toMap()[2610610L]!!
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
