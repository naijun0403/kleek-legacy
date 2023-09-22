package app.kleek.reflow.script.event.user

import app.kleek.reflow.compat.channel.IChannel
import app.kleek.reflow.compat.member.UserInfo
import app.kleek.reflow.script.event.Event

class UserJoinEvent(
    val channel: IChannel,
    val users: List<UserInfo>,
    val inviter: UserInfo? = null
) : Event {

    override val name: String = "user_join"

}