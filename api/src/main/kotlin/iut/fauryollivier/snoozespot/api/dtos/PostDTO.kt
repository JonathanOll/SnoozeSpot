package iut.fauryollivier.snoozespot.api.dtos

import iut.fauryollivier.snoozespot.api.entities.Post
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class PostDTO(

    val id: Int,
    val user: UserDTO,
    val content: String,
    val likeCount: Int,
    @Contextual val createdAt: LocalDateTime?,
    val pictures: List<StoredFileDTO> = emptyList(),
    val comments: List<PostCommentDTO> = emptyList()

) : DTOBase() {

    override fun toEntity() = Post(
        id = id,
        user = user.toEntity(),
        content = content,
        likeCount = likeCount,
        createdAt = createdAt ?: LocalDateTime.now(),
        pictures = pictures.map { it.toEntity() },
        comments = comments.map { it.toEntity() }
    )

}
