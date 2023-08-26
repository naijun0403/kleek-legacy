package app.kleek.reflow.compat.member

import app.kleek.reflow.config.VersionConfig
import app.kleek.reflow.inapp.member.NativeChatMemberSet

class ChatMemberSet(
    private val nativeChatMemberSet: NativeChatMemberSet,
    private val classloader: ClassLoader,
    private val versionConfig: VersionConfig
) {

    @Suppress("UNCHECKED_CAST")
    fun getMembers(): List<Long> {
        val watermarkClass = classloader.loadClass(versionConfig.watermarkClass)
        val watermark = nativeChatMemberSet.chatMemberSetClass.getDeclaredField(versionConfig.chatMemberSetWatermarkManagerField).run {
            isAccessible = true
            get(nativeChatMemberSet.chatMemberSet) ?: throw IllegalStateException("watermark is null")
        }

        val members = watermarkClass.getDeclaredField(versionConfig.watermarkMemberListField).run {
            isAccessible = true
            get(watermark) ?: throw IllegalStateException("members is null")
        }

        return members as List<Long>
    }

}