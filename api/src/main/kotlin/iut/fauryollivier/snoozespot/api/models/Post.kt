package iut.fauryollivier.snoozespot.api.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Post(
    val id: Int,
    val user: User,
    val content: String,
    val likeCount: Int,
    @Contextual val createdAt: LocalDateTime,
    val pictures: List<StoredFile> = emptyList(),
    val comments: List<PostComment> = emptyList()
)
