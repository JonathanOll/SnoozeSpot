package iut.fauryollivier.snoozespot.api.entities

import iut.fauryollivier.snoozespot.api.models.SpotModel
import java.time.LocalDateTime

data class Spot(

    val id: Int,
    val creator: Int?,
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val canBeDisplayed: Int,
    val likeCount: Int,
    val rating: Float?,
    val createdAt: LocalDateTime,
    val deletedAt: LocalDateTime?,
    val pictures: List<Int>,
    val attributes: List<Int>,
    val comments: List<Int>,

) : EntityBase() {

    override fun toModel(): SpotModel = SpotModel(
        id = this.id,
        creatorId = this.creator,
        name = this.name,
        description = this.description,
        latitude = this.latitude,
        longitude = this.longitude,
        canBeDisplayed = this.canBeDisplayed,
        likeCount = this.likeCount,
        rating = this.rating,
        createdAt = this.createdAt,
        deletedAt = this.deletedAt,
        picturesIds = this.pictures,
        attributesIds = this.attributes,
        commentsIds = this.comments,
    )

}