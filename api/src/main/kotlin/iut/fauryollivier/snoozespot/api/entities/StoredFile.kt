package iut.fauryollivier.snoozespot.api.entities

import iut.fauryollivier.snoozespot.api.dtos.StoredFileDTO
import java.time.LocalDateTime

data class StoredFile(

    val id: Int,
    val name: String,
    val path: String,
    val type: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
    val deletedAt: LocalDateTime? = null

) : EntityBase() {

    override fun toDTO() = StoredFileDTO(
        id = id,
        name = name,
        path = path,
        type = type,
        createdAt = createdAt
    )

}