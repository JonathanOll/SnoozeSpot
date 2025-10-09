package iut.fauryollivier.snoozespot.api.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class StoredFile(
    val id: Int,
    val name: String,
    val path: String,
    val type: String,
    @Contextual val createdAt: LocalDateTime,
)
