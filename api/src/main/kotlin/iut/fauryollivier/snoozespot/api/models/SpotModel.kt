package iut.fauryollivier.snoozespot.api.models

import iut.fauryollivier.snoozespot.api.dtos.SpotDTO
import iut.fauryollivier.snoozespot.api.entities.Spot
import java.time.LocalDateTime

data class SpotModel(

    val id: Int,
    val creatorId: Int?,
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val canBeDisplayed: Int,
    val likeCount: Int,
    val rating: Float?,
    val createdAt: LocalDateTime,
    val deletedAt: LocalDateTime?,
    val picturesIds: List<Int>,
    val attributesIds: List<Int>,
    val commentsIds: List<Int>,

    val creator: UserModel? = null,
    val pictures: List<StoredFileModel>? = null,
    val attributes: List<SpotAttributeModel>? = null,
    val comments: List<SpotCommentModel>? = null

) : ModelBase() {

    override fun toEntity(): Spot = Spot (
        id = this.id,
        creator = this.creatorId,
        name = this.name,
        description = this.description,
        latitude = this.latitude,
        longitude = this.longitude,
        canBeDisplayed = this.canBeDisplayed,
        likeCount = this.likeCount,
        rating = this.rating,
        createdAt = this.createdAt,
        deletedAt = this.deletedAt,
        pictures = this.picturesIds,
        attributes = this.attributesIds,
        comments = this.commentsIds,
    )

    override fun toDTO(loadRelations: Boolean) : SpotDTO = SpotDTO(
        id = id,
        creator = if(loadRelations) creator?.toDTO(false) else null,
        name = name,
        description = description,
        latitude = latitude,
        longitude = longitude,
        likeCount = likeCount,
        rating = rating,
        createdAt = createdAt,
        pictures = if(loadRelations) pictures?.map { it.toDTO(false) } else null,
        attributes = if(loadRelations) attributes?.map { it.toDTO(false) } else null,
        comments = if(loadRelations) comments?.map { it.toDTO(false) } else null
    )
}