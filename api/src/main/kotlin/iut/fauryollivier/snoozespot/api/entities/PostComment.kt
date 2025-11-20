package iut.fauryollivier.snoozespot.api.entities

import iut.fauryollivier.snoozespot.api.dtos.PostCommentDTO
import java.time.LocalDateTime

data class PostComment(

    val id: Int,
    val user: User,
    val content: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
    val deletedAt: LocalDateTime? = null

) : EntityBase() {

    override fun toDTO() = PostCommentDTO(
        id = id,
        user = user.toDTO(),
        content = content,
        createdAt = createdAt
    )

}