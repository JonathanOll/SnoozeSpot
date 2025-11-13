package iut.fauryollivier.snoozespot.api.models

import com.fasterxml.jackson.databind.ser.std.UUIDSerializer
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.UUID
import kotlin.uuid.Uuid

@Serializable
data class User(
    @Contextual val uuid: UUID,
    val username: String,
    val email: String?,
    val profilePicture: StoredFile?,
    val karma: Int,
    @Contextual val createdAt: LocalDateTime,
    val spots: List<Spot> = emptyList(),
    val posts: List<Post> = emptyList(),
    val following: List<User> = emptyList(),
    val follower: List<User> = emptyList()
)
