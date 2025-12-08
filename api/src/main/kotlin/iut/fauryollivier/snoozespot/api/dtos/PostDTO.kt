package iut.fauryollivier.snoozespot.api.dtos

import iut.fauryollivier.snoozespot.api.model.PostModel
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class PostDTO(

    val id: Int,
    val user: UserDTO?,
    val content: String,
    val likeCount: Int,
    @Contextual val createdAt: LocalDateTime?,

    val pictures: List<StoredFileDTO>? = emptyList(),
    val comments: List<PostCommentDTO>? = emptyList()

) : DTOBase() {

    override fun toModel() = PostModel(
        id = id,
        userId = 0,
        content = content,
        likeCount = likeCount,
        createdAt = createdAt ?: LocalDateTime.now(),
        picturesIds = emptyList(),
        commentsIds = emptyList(),

        user = user?.toModel(),
        pictures = pictures?.map { it.toModel() },
        comments = comments?.map { it.toModel() }
    )

}
