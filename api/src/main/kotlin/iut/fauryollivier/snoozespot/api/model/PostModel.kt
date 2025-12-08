package iut.fauryollivier.snoozespot.api.model

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

    override fun toDTO() = PostDTO(
        id = id,
        user = user?.toDTO(),
        content = content,
        likeCount = likeCount,
        createdAt = createdAt,
        pictures = pictures?.map { it.toDTO() },
        comments = comments?.map { it.toDTO() }
    )

}