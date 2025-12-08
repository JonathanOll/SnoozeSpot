package iut.fauryollivier.snoozespot.api.dtos

import iut.fauryollivier.snoozespot.api.config.Config
import iut.fauryollivier.snoozespot.api.enums.StoredFileType
import iut.fauryollivier.snoozespot.api.model.StoredFileModel
import iut.fauryollivier.snoozespot.api.enums.StoredFileUsage
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.UUID

@Serializable
data class StoredFileDTO(

    @Contextual val uuid: UUID,
    val description: String,
    val path: String,
    val type: StoredFileType,
    val usage: StoredFileUsage,
    @Contextual val createdAt: LocalDateTime?,

    ) : DTOBase() {

    override fun toModel() = StoredFileModel(
        id = null,
        uuid = uuid,
        description = description,
        path = Config.STORED_FILE_PATH + path,
        type = type,
        usage = usage,
        canBeUsed = true,
        createdAt = createdAt ?: LocalDateTime.now()
    )

}
