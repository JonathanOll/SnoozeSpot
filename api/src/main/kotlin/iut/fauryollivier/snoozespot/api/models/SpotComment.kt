package iut.fauryollivier.snoozespot.api.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class SpotComment(
    val id: Int,
    val user: User,
    val description: String,
    val rating: Int,
    @Contextual val createdAt: LocalDateTime,
)
