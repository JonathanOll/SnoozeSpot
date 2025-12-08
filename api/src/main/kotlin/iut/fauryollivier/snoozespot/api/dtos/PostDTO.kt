package iut.fauryollivier.snoozespot.api.dtos

import iut.fauryollivier.snoozespot.api.models.PostModel
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

    override fun toModel(loadRelations: Boolean) : PostModel = PostModel(
        id = id,
        userId = 0,
        content = content,
        canBeDisplayed = false,
        likeCount = likeCount,
        createdAt = createdAt ?: LocalDateTime.now(),
        picturesIds = emptyList(),
        commentsIds = emptyList(),

        user = if(loadRelations) user?.toModel(false) else null,
        pictures = if(loadRelations) pictures?.map { it.toModel(false) } else null,
        comments = if(loadRelations) comments?.map { it.toModel(false) } else null
    )

}
