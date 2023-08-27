package app.kleek.reflow.compat.chat.model

data class ReplyModel(
    val logId: Long,
    val userId: Long,
    val type: Int,
    val message: String,
    val linkId: Long
)
