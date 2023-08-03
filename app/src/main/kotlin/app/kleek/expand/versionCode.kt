package app.kleek.expand

import android.content.pm.ApplicationInfo

val ApplicationInfo.versionCode: Long
    get() = ApplicationInfo::class.java.getField("longVersionCode").getLong(this)