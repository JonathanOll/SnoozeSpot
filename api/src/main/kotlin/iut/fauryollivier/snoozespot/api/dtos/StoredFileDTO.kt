package iut.fauryollivier.snoozespot.api.dtos

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class StoredFileDTO(

    val id: Int,
    val name: String,
    val path: String,
    val type: String,
    @Contextual val createdAt: LocalDateTime,

) : DTOBase() {

    override fun toEntity() = iut.fauryollivier.snoozespot.api.entities.StoredFile(
        id = id,
        name = name,
        path = path,
        type = type,
        createdAt = createdAt
    )

}
