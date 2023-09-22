package app.kleek.reflow.script.event.user

import app.kleek.reflow.compat.channel.IChannel
import app.kleek.reflow.compat.member.UserInfo
import app.kleek.reflow.script.event.Event

class UserQuitEvent(
    val channel: IChannel,
    val user: UserInfo,
    val hidden: Boolean = false
) : Event {

    override val name: String = "user_quit"

}