package app.kleek.reflow.logger

import android.util.Log
import de.robv.android.xposed.XposedBridge

object Logger {

    fun log(message: String) {
        XposedBridge.log(message)
        Log.d("kleek", message)
    }

}