package iut.fauryollivier.snoozespot.api.model

import iut.fauryollivier.snoozespot.api.config.Config
import iut.fauryollivier.snoozespot.api.enums.StoredFileType
import iut.fauryollivier.snoozespot.api.dtos.StoredFileDTO
import iut.fauryollivier.snoozespot.api.entities.EntityBase
import iut.fauryollivier.snoozespot.api.entities.StoredFile
import iut.fauryollivier.snoozespot.api.enums.StoredFileUsage
import java.time.LocalDateTime
import java.util.UUID

data class StoredFileModel(

    val id: Int?,
    val uuid: UUID,
    val description: String,
    val path: String,
    val type: StoredFileType,
    val usage: StoredFileUsage,
    val canBeUsed: Boolean,
    val createdAt: LocalDateTime,
    val deletedAt: LocalDateTime? = null

) : ModelBase() {

    override fun toEntity(): StoredFile = StoredFile(
        id = this.id,
        uuid = this.uuid,
        description = this.description,
        path = this.path,
        type = this.type,
        usage = this.usage,
        canBeUsed = this.canBeUsed,
        createdAt = this.createdAt,
        deletedAt = this.deletedAt
    )

    override fun toDTO() = StoredFileDTO(
        uuid = uuid,
        description = description,
        path = Config.STORED_FILE_REMOTE_PATH + path.substringAfter(Config.STORED_FILE_PATH.substringAfterLast("\\")),
        type = type,
        usage = usage,
        createdAt = createdAt
    )
}