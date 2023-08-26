package app.kleek.reflow.config

import kotlinx.serialization.Serializable

@Serializable
data class VersionConfig(
    val locoClassName: String,
    val locoRequestMethod: String,
    val locoResponseMethod: String,

    val locoProtocolClass: String,
    val locoHeaderClass: String,
    val locoBodyClass: String,
    val locoMethodClass: String,

    val locoGetHeaderField: String,
    val locoGetBodyField: String,

    val locoPacketIdField: String,
    val locoStatusField: String,
    val locoMethodField: String,

    val locoMethodBytesMethod: String,
    val locoMethodNameMethod: String,

    val locoBodyToMap: String,

    val locoReqClass: String,
    val locoResClass: String,

    val chatTypeClass: String,
    val chatTypeFinderClass: String,
    val chatTypeFinderFindMethod: String,

    val chatSendingLogClass: String,
    val chatSendingLogBuilderClass: String,

    val chatSendingLogBuilderMessageProperty: String,
    val chatSendingLogBuilderAttachmentProperty: String,
    val chatSendingLogBuilderBuildMethod: String,

    val chatSendingLogRequestClass: String,
    val chatSendingTypeClass: String,

    val chatLogSendingMethod: String,

    val chatRoomClass: String,

    val chatRoomListManagerClass: String,
    val chatRoomListRealManagerClass: String,

    val chatRoomListManagerProperty: String,
    val chatRoomListRealProperty: String,

    val chatRoomChannelIdField: String,

    val chatRoomLinkIdField: String,

    val chatRoomTypeMethod: String,

    val chatRoomNameMethod: String,

    val chatRoomMemberSetMethod: String,

    val parseChannelIdMethod: String,

    val sendEventHandlerClass: String,

    val chatMemberSetClass: String,
    val chatMemberSetWatermarkManagerField: String,

    val chatRoomTypeClass: String,

    val watermarkClass: String,
    val watermarkMemberListField: String,
)
