package iut.fauryollivier.snoozespot.api.entities

import iut.fauryollivier.snoozespot.api.config.Config
import iut.fauryollivier.snoozespot.api.enums.StoredFileType
import iut.fauryollivier.snoozespot.api.dtos.StoredFileDTO
import iut.fauryollivier.snoozespot.api.enums.StoredFileUsage
import java.nio.file.Path
import java.time.LocalDateTime
import java.util.UUID
import kotlin.io.path.Path

data class StoredFile(

    val id: Int?,
    val uuid: UUID,
    val description: String,
    val path: String,
    val type: StoredFileType,
    val usage: StoredFileUsage,
    val createdAt: LocalDateTime,
    val deletedAt: LocalDateTime? = null

) : EntityBase() {

    override fun toDTO() = StoredFileDTO(
        uuid = uuid,
        description = description,
        path = path.substringAfter(Config.STORED_FILE_PATH.substringAfterLast("\\")),
        type = type,
        usage = usage,
        createdAt = createdAt
    )
}