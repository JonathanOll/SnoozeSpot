package iut.fauryollivier.snoozespot.api.model

import iut.fauryollivier.snoozespot.api.dtos.SpotAttributeDTO
import iut.fauryollivier.snoozespot.api.entities.EntityBase
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

    override fun toEntity(): EntityBase {
        TODO("Not yet implemented")
    }

    override fun toDTO() = SpotAttributeDTO(
        id = id,
        name = name,
        icon = icon?.toDTO()
    )

}