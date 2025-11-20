package iut.fauryollivier.snoozespot.api.dtos

import iut.fauryollivier.snoozespot.api.entities.SpotAttribute
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class SpotAttributeDTO(

    val id: Int,
    val name: String,
    val icon: StoredFileDTO?

) : DTOBase() {

    override fun toEntity() = SpotAttribute(
        id = id,
        name = name,
        icon = icon?.toEntity(),
        createdAt = LocalDateTime.now(),
        updatedAt = null,
        deletedAt = null
    )

}
