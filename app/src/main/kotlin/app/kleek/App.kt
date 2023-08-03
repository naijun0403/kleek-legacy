package app.kleek

import android.app.Application
import android.content.Intent
import app.kleek.core.CoreHelper
import app.kleek.core.constant.Constant
import app.kleek.view.activity.InitialSetupActivity
import com.topjohnwu.superuser.io.SuFile

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        CoreHelper.contextGetter = { this }

        val sharedPreferences = getSharedPreferences("AppPrefs", MODE_PRIVATE)
        val isFirstRun = sharedPreferences.getBoolean("isFirstRun", true)

        if (isFirstRun) {
            Intent(this, InitialSetupActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(this)
            }
        }
    }

}