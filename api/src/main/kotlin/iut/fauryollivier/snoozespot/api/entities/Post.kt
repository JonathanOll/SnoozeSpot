package iut.fauryollivier.snoozespot.api.entities

import iut.fauryollivier.snoozespot.api.dtos.PostDTO
import java.time.LocalDateTime

data class Post(

    val id: Int,
    val user: User,
    val content: String,
    val likeCount: Int,
    val createdAt: LocalDateTime,
    val deletedAt: LocalDateTime? = null,
    val pictures: List<StoredFile> = emptyList(),
    val comments: List<PostComment> = emptyList()

) : EntityBase() {

    override fun toDTO() = PostDTO(
        id = id,
        user = user.toDTO(),
        content = content,
        likeCount = likeCount,
        createdAt = createdAt,
        pictures = pictures.map { it.toDTO() },
        comments = comments.map { it.toDTO() }
    )

}