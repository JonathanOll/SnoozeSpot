package iut.fauryollivier.snoozespot.api.entities

import iut.fauryollivier.snoozespot.api.enums.StoredFileType
import iut.fauryollivier.snoozespot.api.enums.StoredFileUsage
import iut.fauryollivier.snoozespot.api.models.StoredFileModel
import java.time.LocalDateTime
import java.util.UUID

data class StoredFile(

    val id: Int?,
    val uuid: UUID,
    val description: String,
    val path: String,
    val type: StoredFileType,
    val usage: StoredFileUsage,
    val canBeUsed: Boolean,
    val createdAt: LocalDateTime,
    val deletedAt: LocalDateTime? = null

) : EntityBase() {

    override fun toModel(): StoredFileModel = StoredFileModel(
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

}