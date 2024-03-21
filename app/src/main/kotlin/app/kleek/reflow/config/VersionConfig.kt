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

    val locoBodyToMapMethod: String, // 10.3.6 이후로 안씀 (사라짐)
    val locoBodyBSONObjectField: String,
    val locoBodyBSONToMapMethod: String,

    val locoReqClass: String,
    val locoResClass: String,

    val chatTypeClass: String,
    val chatTypeFinderClass: String,
    val chatTypeFinderFindMethod: String,

    val chatSendingLogClass: String,
    val chatSendingLogBuilderClass: String,

    val chatSendingLogBuilderMessageProperty: String,
    val chatSendingLogBuilderAttachmentProperty: String,
    val chatSendingLogBuilderPhotosProperty: String,
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

    val oauthHelperClass: String,
    val oauthHelperStaticClass: String,
    val oauthHelperStaticInstanceField: String,
    val oauthHelperGetTokenMethod: String,
    val oauthHelperGetAuthorization: String,

    val hardwareClass: String,
    val hardwareInstanceField: String,
    val hardwareGetDeviceUUIDMethod: String,

    val chatLogClass: String,

    val chatLogLogIdField: String,
    val chatLogChatIdField: String,
    val chatLogTypeField: String,
    val chatLogAuthorIdField: String,
    val chatLogAttachmentField: String,
    val chatLogMessageField: String,
    val chatLogSendAtField: String,
    val chatLogMessageIdField: String,
    val chatLogPrevIdField: String,
    val chatLogRefererField: String,
    val chatLogSupplementField: String,

    val photoModelClass: String,

    val talkPreferencesQualityDataClass: String,
    val talkPreferencesQualityFinderClass: String,
    val talkPreferencesQualityFinderMethod: String,

)
