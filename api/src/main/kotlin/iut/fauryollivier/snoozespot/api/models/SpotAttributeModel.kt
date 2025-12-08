package iut.fauryollivier.snoozespot.api.models

import iut.fauryollivier.snoozespot.api.dtos.SpotAttributeDTO
import iut.fauryollivier.snoozespot.api.entities.SpotAttribute
import java.time.LocalDateTime

data class SpotAttributeModel(

    val id: Int,
    val name: String,
    val iconId: Int?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?,
    val deletedAt: LocalDateTime? = null,

    val icon: StoredFileModel? = null

) : ModelBase() {

    override fun toEntity(): SpotAttribute = SpotAttribute(
        id = id,
        name = name,
        iconId = iconId,
        createdAt = createdAt,
        updatedAt = updatedAt,
        deletedAt = deletedAt
    )

    override fun toDTO(loadRelations: Boolean) : SpotAttributeDTO = SpotAttributeDTO(
        id = id,
        name = name,
        icon = if(loadRelations) icon?.toDTO(false) else null
    )

}