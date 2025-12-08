package iut.fauryollivier.snoozespot.api.model

import iut.fauryollivier.snoozespot.api.dtos.SpotCommentDTO
import iut.fauryollivier.snoozespot.api.entities.SpotComment
import java.time.LocalDateTime

data class SpotCommentModel(

    val id: Int,
    val spotId: Int,
    val userId: Int,
    val description: String,
    val rating: Int,
    val createdAt: LocalDateTime,
    val deletedAt: LocalDateTime? = null,

    val spot: Int? = null,
    val user: UserModel? = null

) : ModelBase() {

    override fun toEntity(): SpotComment = SpotComment(
        id = this.id,
        spotId = this.spotId,
        userId = this.userId,
        description = this.description,
        rating = this.rating,
        createdAt = this.createdAt,
        deletedAt = this.deletedAt,
    )

    override fun toDTO() = SpotCommentDTO(
        id = id,
        user = user?.toDTO(),
        description = description,
        rating = rating,
        createdAt = createdAt
    )

}