package iut.fauryollivier.snoozespot.api.dtos

import iut.fauryollivier.snoozespot.api.models.SpotModel
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
    @Contextual val createdAt: LocalDateTime? = null,
    val pictures: List<StoredFileDTO>? = null,
    val attributes: List<SpotAttributeDTO>? = null,
    val comments: List<SpotCommentDTO>? = null

) : DTOBase() {

    override fun toModel(loadRelations: Boolean) : SpotModel = SpotModel(
        id = id,
        creatorId = null,
        name = name,
        description = description,
        latitude = latitude,
        longitude = longitude,
        canBeDisplayed = 1,
        likeCount = likeCount,
        rating = rating,
        createdAt = createdAt ?: LocalDateTime.now(),
        deletedAt = null,
        picturesIds = emptyList(),
        attributesIds = emptyList(),
        commentsIds = emptyList(),
        creator = if(loadRelations) creator?.toModel(false) else null,
        pictures = if(loadRelations) pictures?.map { it.toModel(false) } else null,
        attributes = if(loadRelations) attributes?.map { it.toModel(false) } else null,
        comments = if(loadRelations) comments?.map { it.toModel(false) } else null
    )
}
