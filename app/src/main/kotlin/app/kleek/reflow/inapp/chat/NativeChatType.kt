package app.kleek.reflow.inapp.chat

import app.kleek.core.CoreHelper

object NativeChatType {

    fun toAppType(type: Int): Any {
        val versionConfig = CoreHelper.versionConfigGetter!!.invoke()
        val classLoader = CoreHelper.classLoaderGetter!!.invoke()

        val typeClass = classLoader.loadClass(versionConfig.chatTypeFinderClass)
        val instance = typeClass.newInstance()

        return typeClass.getDeclaredMethod(versionConfig.chatTypeFinderFindMethod, Int::class.java)
            .invoke(instance, type)!!
    }

}