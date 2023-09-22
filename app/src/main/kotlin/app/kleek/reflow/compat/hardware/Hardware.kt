package app.kleek.reflow.compat.hardware

import app.kleek.core.CoreHelper
import app.kleek.reflow.inapp.hardware.NativeHardware

class Hardware(
    private val nativeHardware: NativeHardware
) {

    val deviceUUID: String get() {
        val versionConfig = CoreHelper.versionConfigGetter!!.invoke()

        nativeHardware.hardwareClass.getDeclaredMethod(versionConfig.hardwareGetDeviceUUIDMethod).apply {
            return invoke(nativeHardware.hardware) as String
        }
    }

}