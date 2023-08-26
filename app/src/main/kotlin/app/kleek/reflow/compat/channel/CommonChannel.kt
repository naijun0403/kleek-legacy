package app.kleek.reflow.compat.channel

import app.kleek.core.CoreHelper
import app.kleek.reflow.compat.member.ChatMemberSet
import app.kleek.reflow.config.VersionConfig
import app.kleek.reflow.inapp.channel.NativeChannel
import app.kleek.reflow.inapp.channel.NativeChannelType
import app.kleek.reflow.inapp.member.NativeChatMemberSet
import java.util.concurrent.Future

/**
 * Base for all channels (normal, open)
 */
class CommonChannel(
    val nativeChannel: NativeChannel
) : IChannel {

    val classLoader: ClassLoader = CoreHelper.classLoaderGetter?.invoke() ?: throw IllegalStateException("classLoader is null")
    val versionConfig: VersionConfig = CoreHelper.versionConfigGetter?.invoke() ?: throw IllegalStateException("versionConfig is null")

    override val channelId: Long
        get() {
            val versionConfig = CoreHelper.versionConfigGetter?.invoke() ?: return -1

            nativeChannel.chatRoomClass.getDeclaredField(versionConfig.chatRoomChannelIdField).apply {
                isAccessible = true
                return get(nativeChannel) as Long
            }
        }

    override fun isOpenChannel(): Boolean {
        return getChannelType() == ChannelType.OpenMulti || getChannelType() == ChannelType.OpenDirect
    }

    override fun getMembers(): List<Long> {
        val nativeChatMemberSet = nativeChannel.chatRoomClass.getMethod(versionConfig.chatRoomMemberSetMethod).run {
            isAccessible = true
            invoke(nativeChannel.channel) ?: throw IllegalStateException("nativeChatMemberSet is null")
        }
        val nativeModel = NativeChatMemberSet(nativeChatMemberSet, classLoader.loadClass(versionConfig.chatMemberSetClass))
        val modern = ChatMemberSet(nativeModel, classLoader, versionConfig)

        return modern.getMembers()
    }

    override fun getChannelName(): String {
        nativeChannel.chatRoomClass.getMethod(versionConfig.chatRoomNameMethod).run {
            isAccessible = true
            return invoke(nativeChannel.channel) as String
        }
    }

    override fun getChannelType(): ChannelType {
        val chatRoomTypeClass = classLoader.loadClass(versionConfig.chatRoomTypeClass)
        val chatRoomType = nativeChannel.chatRoomClass.getDeclaredField(versionConfig.chatRoomTypeMethod).run {
            isAccessible = true
            get(nativeChannel.channel) ?: throw IllegalStateException("chatRoomType is null")
        }

        val nativeModel = NativeChannelType(chatRoomType, chatRoomTypeClass)

        return ChannelType.fromNative(nativeModel)
    }

    override fun sendMessage(message: String): Future<Boolean> {
        TODO("Not yet implemented")
    }

}