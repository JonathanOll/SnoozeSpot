package iut.fauryollivier.snoozespot.api.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class PostComment(
    val id: Int,
    val user: User,
    val content: String,
    @Contextual val createdAt: LocalDateTime,
)
