package iut.fauryollivier.snoozespot.api.dtos

import iut.fauryollivier.snoozespot.api.models.PostCommentModel
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class PostCommentDTO(

    val id: Int,
    val user: UserDTO? = null,
    val content: String,
    @Contextual val createdAt: LocalDateTime? = null

) : DTOBase() {

    override fun toModel(loadRelations: Boolean): PostCommentModel = PostCommentModel(
        id = id,
        userId = 0,
        content = content,
        createdAt = createdAt ?: LocalDateTime.now(),

        user = if (loadRelations) user?.toModel(false) else null
    )

}
