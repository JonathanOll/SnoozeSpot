package iut.fauryollivier.snoozespot.api.entities

import iut.fauryollivier.snoozespot.api.model.PostCommentModel
import java.time.LocalDateTime

data class PostComment(

    val id: Int,
    val userId: Int,
    val content: String,
    val createdAt: LocalDateTime,
    val deletedAt: LocalDateTime? = null,

) : EntityBase() {

    override fun toModel(): PostCommentModel = PostCommentModel(
        id = this.id,
        userId = this.userId,
        content = this.content,
        createdAt = this.createdAt,
        deletedAt = this.deletedAt,
    )
}