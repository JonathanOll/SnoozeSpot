package iut.fauryollivier.snoozespot.api.entities

import iut.fauryollivier.snoozespot.api.dtos.UserDTO
import java.time.LocalDateTime
import java.util.UUID

data class User(

    val id: Int?,
    val uuid: UUID,
    val username: String,
    val email: String?,
    val profilePicture: StoredFile?,
    val karma: Int,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime? = null,
    val deletedAt: LocalDateTime? = null,
    val spots: List<Spot> = emptyList(),
    val posts: List<Post> = emptyList(),
    val following: List<User> = emptyList(),
    val followers: List<User> = emptyList()

) : EntityBase() {

    override fun toDTO(): UserDTO = UserDTO(
        uuid = uuid,
        username = username,
        email = email,
        profilePicture = profilePicture?.toDTO(),
        karma = karma,
        createdAt = createdAt,
        spots = spots.map { it.toDTO() },
        posts = posts.map { it.toDTO() },
        following = following.map { it.toDTO() },
        followers = followers.map { it.toDTO() }
    )

}