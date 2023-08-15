package app.kleek.reflow.inapp.channel

data class NativeChannel(
    val channel: Any,
    val chatRoomClass: Class<*>,
)
