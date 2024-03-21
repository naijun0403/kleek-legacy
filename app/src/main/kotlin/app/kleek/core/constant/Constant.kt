package app.kleek.core.constant

import android.annotation.SuppressLint

object Constant {

    @SuppressLint("SdCardPath")
    const val optionPath = "/data/system/kleek/kleek_option.json" // 유저 옵션

    @SuppressLint("SdCardPath")
    const val configPath = "/data/system/kleek/kleek_config.toml" // 버전 정보

    const val version = "0.0.5 (dev)" // 버전

}