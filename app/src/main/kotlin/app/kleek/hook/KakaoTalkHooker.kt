package app.kleek.hook

import app.kleek.core.CoreHelper
import app.kleek.expand.versionCode
import app.kleek.reflow.config.Config
import app.kleek.reflow.logger.Logger
import app.kleek.viewmodel.SettingModel
import de.robv.android.xposed.IXposedHookLoadPackage
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage

class KakaoTalkHooker : IXposedHookLoadPackage {

    override fun handleLoadPackage(lpparam: XC_LoadPackage.LoadPackageParam) {
        val settings = SettingModel.load()

        Logger.log("test: ${lpparam.packageName}")

        if (lpparam.packageName != settings.packageName || !settings.powerOn) return

        Logger.log("KakaoTalkHooker: ${lpparam.packageName}")

        val config = Config.load()
        val versionConfig = config.getByVersionCode(lpparam.appInfo.versionCode)

        Logger.log("versionConfig: $versionConfig")

        CoreHelper.versionConfigGetter = { versionConfig }
        CoreHelper.classLoaderGetter = { lpparam.classLoader }

        /**
         * Hook LocoRequest
         */
        XposedHelpers.findAndHookMethod(
            versionConfig.locoClassName,
            lpparam.classLoader,
            versionConfig.locoRequestMethod,
            lpparam.classLoader.loadClass(versionConfig.locoReqClass),
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    super.beforeHookedMethod(param)
                    val protocol = param.args[0]
                    Logger.log("[REQ]: $protocol")
                }
            },
        )

        /**
         * Hook LocoResponse
         */
        XposedHelpers.findAndHookMethod(
            versionConfig.locoClassName,
            lpparam.classLoader,
            versionConfig.locoResponseMethod,
            lpparam.classLoader.loadClass(versionConfig.locoResClass),
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam) {
                    super.beforeHookedMethod(param)
                    val protocol = param.args[0]
                    Logger.log("[RES]: $protocol")
                }
            },
        )
    }

}