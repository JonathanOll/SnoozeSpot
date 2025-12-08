package iut.fauryollivier.snoozespot.api.dtos

import iut.fauryollivier.snoozespot.api.models.SpotAttributeModel
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class SpotAttributeDTO(

    val id: Int,
    val name: String,
    val icon: StoredFileDTO? = null

) : DTOBase() {

    override fun toModel(loadRelations: Boolean) : SpotAttributeModel = SpotAttributeModel(
        id = id,
        name = name,
        iconId = null,
        createdAt = LocalDateTime.now(),
        updatedAt = LocalDateTime.now(),
        deletedAt = null,
        icon = if(loadRelations) icon?.toModel(false) else null
    )

}
