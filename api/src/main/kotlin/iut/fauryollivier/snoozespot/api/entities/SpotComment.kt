package iut.fauryollivier.snoozespot.api.entities

import iut.fauryollivier.snoozespot.api.dtos.SpotCommentDTO
import java.time.LocalDateTime

data class SpotComment(

    val id: Int,
    val user: User,
    val description: String,
    val rating: Int,
    val createdAt: LocalDateTime,
    val deletedAt: LocalDateTime? = null

) : EntityBase() {

    override fun toDTO() = SpotCommentDTO(
        id = id,
        user = user.toDTO(),
        description = description,
        rating = rating,
        createdAt = createdAt
    )

}