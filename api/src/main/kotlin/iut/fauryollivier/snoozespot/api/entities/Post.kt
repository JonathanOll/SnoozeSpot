package iut.fauryollivier.snoozespot.api.entities

import iut.fauryollivier.snoozespot.api.model.PostModel
import java.time.LocalDateTime

data class Post(

    val id: Int,
    val userId: Int,
    val content: String,
    val canBeDisplayed: Boolean,
    val likeCount: Int,
    val createdAt: LocalDateTime,
    val deletedAt: LocalDateTime?,
    val pictures: List<Int>,
    val comments: List<Int>

) : EntityBase() {

    override fun toModel(): PostModel = PostModel(
        id = id,
        userId = userId,
        content = content,
        canBeDisplayed = canBeDisplayed,
        likeCount = likeCount,
        createdAt = createdAt,
        deletedAt = deletedAt,
        picturesIds = pictures,
        commentsIds = comments,
    )
}
