package app.kleek.reflow.inapp.channel

import app.kleek.core.CoreHelper

object NativeChannelFinder {

    fun findChannel(channelId: Long): NativeChannel? {
        val versionConfig = CoreHelper.versionConfigGetter?.invoke() ?: return null
        val classLoader = CoreHelper.classLoaderGetter?.invoke() ?: return null

        val managerClass = classLoader.loadClass(versionConfig.chatRoomListManagerClass)
        val subClass = classLoader.loadClass(versionConfig.chatRoomListRealManagerClass)

        val sub = managerClass.getDeclaredField(versionConfig.chatRoomListManagerProperty).get(null)
        val manager = subClass.getDeclaredMethod(versionConfig.chatRoomListRealProperty).invoke(sub)

        return NativeChannel(
            managerClass.getDeclaredMethod(
                versionConfig.parseChannelIdMethod,
                Long::class.java,
                Boolean::class.java
            ).invoke(manager, channelId, true) ?: return null,
            classLoader.loadClass(versionConfig.chatRoomClass)
        )
    }

}