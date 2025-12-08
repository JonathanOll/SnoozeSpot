package iut.fauryollivier.snoozespot.api.dtos

import iut.fauryollivier.snoozespot.api.models.SpotCommentModel
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class SpotCommentDTO(

    val id: Int,
    val spot: SpotDTO? = null,
    val user: UserDTO? = null,
    val description: String,
    val rating: Int,
    @Contextual val createdAt: LocalDateTime? = null

) : DTOBase() {

    override fun toModel(loadRelations: Boolean) : SpotCommentModel = SpotCommentModel(
        id = id,
        spotId = spot?.id ?: 0,
        userId = 0,
        description = description,
        rating = rating,
        createdAt = createdAt ?: LocalDateTime.now(),
        user = if(loadRelations) user?.toModel(false) else null
    )

}
