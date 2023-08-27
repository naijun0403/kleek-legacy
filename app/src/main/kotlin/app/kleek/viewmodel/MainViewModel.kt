package app.kleek.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import app.kleek.core.OptionHelper
import app.kleek.core.constant.Constant
import com.topjohnwu.superuser.Shell
import com.topjohnwu.superuser.io.SuFile
import kotlinx.serialization.Serializable

@Serializable
data class SettingModel(
    val packageName: String,
    val powerOn: Boolean
) {

    companion object {

        fun load(): SettingModel {
            return SettingModel(
                packageName = OptionHelper.readOption("packageName", "com.kakao.talk"),
                powerOn = OptionHelper.readOption("powerOn", false)
            )
        }

        fun save(settingModel: SettingModel) {
            OptionHelper.writeOption("packageName", settingModel.packageName)
            OptionHelper.writeOption("powerOn", settingModel.powerOn)
        }

    }

}

@Serializable
class MainViewModel : ViewModel() {

    fun resetVersionConfig() {
        val context = app.kleek.core.CoreHelper.contextGetter?.invoke() ?: return

        val asset = context.assets.open("config.toml")
        val tomlStr = asset.bufferedReader().use { it.readText() }

        val file = SuFile(Constant.configPath)

        Shell.cmd("rm ${file.absolutePath}").exec()
        Shell.cmd("touch ${file.absolutePath}").exec()
        file.newOutputStream().write(tomlStr.toByteArray())
    }

}