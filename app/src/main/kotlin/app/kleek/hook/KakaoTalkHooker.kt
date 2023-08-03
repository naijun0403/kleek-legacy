package app.kleek.hook

import app.kleek.expand.versionCode
import app.kleek.reflow.config.Config
import app.kleek.reflow.logger.Logger
import app.kleek.viewmodel.SettingModel
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage

class KakaoTalkHooker : IXposedHookLoadPackage {

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        val settings = SettingModel.load()

        if (lpparam.packageName != settings.packageName || !settings.powerOn) return

        Logger.log("KakaoTalkHooker: ${lpparam.packageName}")

        val config = Config.load()
        val versionConfig = config.getByVersionCode(lpparam.appInfo.versionCode)
    }

}