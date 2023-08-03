package app.kleek.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.ViewModelInitializer
import app.kleek.core.OptionHelper
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

    }

}