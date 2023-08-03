package app.kleek.viewmodel

import androidx.compose.runtime.NoLiveLiterals
import androidx.lifecycle.ViewModel
import app.kleek.core.CoreHelper
import app.kleek.core.constant.Constant
import com.topjohnwu.superuser.Shell
import com.topjohnwu.superuser.io.SuFile
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class InitialSetupViewModel : ViewModel() {

    @NoLiveLiterals
    suspend fun createDefaultConfig(): Boolean = withContext(Dispatchers.IO) {
        val context = CoreHelper.contextGetter?.invoke() ?: return@withContext false

        // real
        val asset = context.assets.open("config.toml")
        val tomlStr = asset.bufferedReader().use { it.readText() }

        val file = SuFile(Constant.configPath)

        Shell.cmd("touch ${file.absolutePath}").exec()
        file.newOutputStream().write(tomlStr.toByteArray())

        val optionFile = SuFile(Constant.optionPath)

        Shell.cmd("touch ${optionFile.absolutePath}").exec()

        optionFile.newOutputStream().write(
            buildJsonObject {
                put("packageName", "com.kakao.talk")
                put("powerOn", false)
            }.toString().toByteArray()
        )

        return@withContext true
    }

}