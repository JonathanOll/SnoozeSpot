package iut.fauryollivier.snoozespot.api.dtos

import iut.fauryollivier.snoozespot.api.entities.SpotComment
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class SpotCommentDTO(

    val id: Int,
    val user: UserDTO,
    val description: String,
    val rating: Int,
    @Contextual val createdAt: LocalDateTime,

) : DTOBase() {

    override fun toEntity() = SpotComment(
        id = id,
        user = user.toEntity(),
        description = description,
        rating = rating,
        createdAt = createdAt
    )

}
