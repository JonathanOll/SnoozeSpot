package iut.fauryollivier.snoozespot.api.model

import iut.fauryollivier.snoozespot.api.dtos.PostCommentDTO
import iut.fauryollivier.snoozespot.api.entities.PostComment
import java.time.LocalDateTime

data class PostCommentModel(

    val id: Int,
    val userId: Int,
    val content: String,
    val createdAt: LocalDateTime,
    val deletedAt: LocalDateTime? = null,

    val user: UserModel ? = null,

) : ModelBase() {

    override fun toEntity(): PostComment = PostComment(
        id = id,
        userId = userId,
        content = content,
        createdAt = createdAt,
        deletedAt = deletedAt
    )

    override fun toDTO() = PostCommentDTO(
        id = id,
        user = user?.toDTO(),
        content = content,
        createdAt = createdAt
    )

}