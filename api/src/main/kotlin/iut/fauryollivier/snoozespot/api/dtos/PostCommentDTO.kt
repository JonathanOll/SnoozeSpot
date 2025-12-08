package iut.fauryollivier.snoozespot.api.dtos

import iut.fauryollivier.snoozespot.api.entities.PostComment
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class PostCommentDTO(

    val id: Int,
    val user: UserDTO,
    val content: String,
    @Contextual val createdAt: LocalDateTime?,

) : DTOBase() {

    override fun toEntity(): PostComment = PostComment(
        id = id,
        user = user.toEntity(),
        content = content,
        createdAt = createdAt ?: LocalDateTime.now()
    )

}
