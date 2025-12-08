package iut.fauryollivier.snoozespot.api.models

import iut.fauryollivier.snoozespot.api.dtos.PostDTO
import iut.fauryollivier.snoozespot.api.entities.Post
import java.time.LocalDateTime

data class PostModel(

    val id: Int,
    val userId: Int,
    val content: String,
    val canBeDisplayed: Boolean,
    val likeCount: Int,
    val createdAt: LocalDateTime,
    val deletedAt: LocalDateTime? = null,
    val picturesIds: List<Int>,
    val commentsIds: List<Int>,

    val user: UserModel? = null,
    val pictures: List<StoredFileModel>? = null,
    val comments: List<PostCommentModel>? = null,

) : ModelBase() {

    override fun toEntity(): Post = Post(
        id = id,
        userId = userId,
        content = content,
        canBeDisplayed = canBeDisplayed,
        likeCount = likeCount,
        createdAt = createdAt,
        deletedAt = deletedAt,
        pictures = picturesIds,
        comments = commentsIds
    )

    override fun toDTO(loadRelations: Boolean) : PostDTO = PostDTO(
        id = id,
        user = if(loadRelations) user?.toDTO(false) else null,
        content = content,
        likeCount = likeCount,
        createdAt = createdAt,
        pictures = if(loadRelations) pictures?.map { it.toDTO(false) } else null,
        comments = if(loadRelations) comments?.map { it.toDTO(false) } else null
    )

}