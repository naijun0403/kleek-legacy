package app.kleek.reflow.compat.chat

import app.kleek.reflow.inapp.chat.NativeChatType

enum class ChatType(
    val type: Int
) {
    FEED(0),
    TEXT(1),
    PHOTO(2),
    VIDEO(3),
    CONTACT(4),
    AUDIO(5),
    ANIMATED_EMOTICON(6),
    DIGITAL_ITEM_GIFT(7),
    LINK(9),
    OLD_LOCATION(10),
    AVATAR(11),
    STICKER(12),
    SCHEDULE(13),
    VOTE(14),
    CJ20121212(15),
    LOCATION(16),
    PROFILE(17),
    FILE(18),
    ANIMATED_STICKER(20),
    NUDGE(21),
    SPRITECON(22),
    SHARP_SEARCH(23),
    POST(24),
    ANIMATED_STICKER_EX(25),
    REPLY(26),
    MULTI_PHOTO(27),
    MVOIP(51),
    VOX_ROOM(52),
    LEVERAGE(71),
    ALIMTALK(72),
    PLUS_LEVERAGE(73),
    PLUS(81),
    PLUS_EVENT(82),
    PLUS_VIRAL(83),
    SCHEDULE_FOR_OPEN_LINK(96),
    VOTE_FOR_OPEN_LINK(97),
    POST_FOR_OPEN_LINK(98);

    fun toInappType(): Any {
        return NativeChatType.toAppType(type)
    }

}
