package iut.fauryollivier.snoozespot.api.entities

import iut.fauryollivier.snoozespot.api.dtos.SpotAttributeDTO
import java.time.LocalDateTime

data class SpotAttribute(

    val id: Int,
    val name: String,
    val icon: StoredFile?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
    val deletedAt: LocalDateTime? = null

) : EntityBase() {

    override fun toDTO() = SpotAttributeDTO(
        id = id,
        name = name,
        icon = icon?.toDTO()
    )

}