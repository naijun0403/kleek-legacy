package app.kleek.reflow.compat.channel

import app.kleek.reflow.inapp.channel.NativeChannelType

enum class ChannelType(
    val displayName: String,
    val shortName: String
) {
    NormalDirect("DirectChat", "d"),
    NormalMulti("MultiChat", "m"),
    PlusDirect("PlusChat", "p"),
    SecretDirect("SDirectChat", "sd"),
    SecretMulti("SMultiChat", "sm"),
    OpenDirect("OD", "od"),
    OpenMulti("OM", "om"),
    Memo("MemoChat", "me"),
    Mms("MmsChat", "ms"),
    PlusList("PlusChatList", "pf"),
    ItemDetail("ItemDetailChat", "itemDetail"),
    KeywordLogList("KeywordLogList", "kl"),
    Ad("Ad", "Ad"),
    EventRoom("EventRoom", "EventRoom");

    companion object {
        fun fromNative(native: NativeChannelType): ChannelType {
            if (native.channelType !is Enum<*>) {
                throw IllegalArgumentException("native.channelType is not Enum")
            }

            val displayName = native.channelTypeClass.getMethod("getValue").run {
                isAccessible = true
                invoke(native.channelType) as String
            }

            return values().firstOrNull { it.displayName == displayName } ?: throw IllegalArgumentException("Unknown channel type: $displayName")
        }
    }
}