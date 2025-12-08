package iut.fauryollivier.snoozespot.api.dtos

import iut.fauryollivier.snoozespot.api.model.PostCommentModel
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

    override fun toModel(): PostCommentModel = PostCommentModel(
        id = id,
        userId = 0,
        content = content,
        createdAt = createdAt ?: LocalDateTime.now(),

        user = user?.toModel()
    )

}
