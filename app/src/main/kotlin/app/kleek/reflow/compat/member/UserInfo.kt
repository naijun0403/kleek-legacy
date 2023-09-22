package app.kleek.reflow.compat.member

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserInfo(
    val userId: Long,

    @SerialName("nickName")
    val nickname: String,
)
