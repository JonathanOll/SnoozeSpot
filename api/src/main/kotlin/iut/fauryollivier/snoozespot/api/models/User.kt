package iut.fauryollivier.snoozespot.api.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class User(
    val id: Int,
    val username: String,
    val email: String,
    val profilePicture: File?,
    val karma: Int,
    @Contextual val createdAt: LocalDateTime,
    val spots: List<Spot> = emptyList(),
    val posts: List<Post> = emptyList(),
    val following: List<User> = emptyList(),
    val follower: List<User> = emptyList()
)
