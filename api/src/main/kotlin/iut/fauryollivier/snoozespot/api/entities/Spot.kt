package iut.fauryollivier.snoozespot.api.entities

import iut.fauryollivier.snoozespot.api.dtos.SpotDTO
import java.time.LocalDateTime

data class Spot(

    val id: Int,
    val creator: User?,
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val canBeDisplayed: Int,
    val likeCount: Int,
    val rating: Float? = null,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
    val deletedAt: LocalDateTime? = null,
    val pictures: List<StoredFile> = emptyList(),
    val attributes: List<SpotAttribute> = emptyList(),
    val comments: List<SpotComment> = emptyList()

) : EntityBase() {

    override fun toDTO() = SpotDTO(
        id = id,
        creator = creator?.toDTO(),
        name = name,
        description = description,
        latitude = latitude,
        longitude = longitude,
        likeCount = likeCount,
        rating = rating,
        createdAt = createdAt,
        pictures = pictures.map { it.toDTO() },
        attributes = attributes.map { it.toDTO() },
        comments = comments.map { it.toDTO() }
    )
}