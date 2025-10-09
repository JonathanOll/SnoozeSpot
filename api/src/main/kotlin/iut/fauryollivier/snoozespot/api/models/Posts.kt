package iut.fauryollivier.snoozespot.api.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Post(
    val id: Int,
    val userId: Int,
    val content: String,
    val likeCount: Int = 0,
    val canBeDisplayed: Boolean = true,
    @Contextual val createdAt: LocalDateTime,
    @Contextual val deletedAt: LocalDateTime? = null
)
