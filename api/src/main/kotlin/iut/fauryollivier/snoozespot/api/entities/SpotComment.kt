package iut.fauryollivier.snoozespot.api.entities

import iut.fauryollivier.snoozespot.api.models.SpotCommentModel
import java.time.LocalDateTime

data class SpotComment(

    val id: Int,
    val spotId: Int,
    val userId: Int,
    val description: String,
    val rating: Int,
    val createdAt: LocalDateTime,
    val deletedAt: LocalDateTime? = null

) : EntityBase() {

    override fun toModel(): SpotCommentModel = SpotCommentModel(
        id = this.id,
        spotId = this.spotId,
        userId = this.userId,
        description = this.description,
        rating = this.rating,
        createdAt = this.createdAt,
        deletedAt = this.deletedAt,
    )
}