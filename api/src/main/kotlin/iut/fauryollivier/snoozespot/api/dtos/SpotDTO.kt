package iut.fauryollivier.snoozespot.api.dtos

import iut.fauryollivier.snoozespot.api.entities.Spot
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class SpotDTO(

    val id: Int,
    val creator: UserDTO? = null,
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val likeCount: Int,
    val rating: Float? = null,
    @Contextual val createdAt: LocalDateTime?,
    val pictures: List<StoredFileDTO> = emptyList(),
    val attributes: List<SpotAttributeDTO> = emptyList(),
    val comments: List<SpotCommentDTO> = emptyList(),

    ) : DTOBase() {

    override fun toEntity() = Spot(
        id = id,
        creator = creator?.toEntity(),
        name = name,
        description = description,
        latitude = latitude,
        longitude = longitude,
        likeCount = likeCount,
        rating = rating,
        createdAt = createdAt ?: LocalDateTime.now(),
        pictures = pictures.map { it.toEntity() },
        attributes = attributes.map { it.toEntity() },
        comments = comments.map { it.toEntity() },

        canBeDisplayed = 1,
        updatedAt = LocalDateTime.now(),
        deletedAt = null,
    )

}
