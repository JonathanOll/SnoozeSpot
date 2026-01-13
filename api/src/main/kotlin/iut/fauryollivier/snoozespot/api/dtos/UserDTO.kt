package iut.fauryollivier.snoozespot.api.dtos

import iut.fauryollivier.snoozespot.api.entities.User
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.*

@Serializable
data class UserDTO(

    @Contextual val uuid: UUID,
    val username: String,
    val email: String?,
    val profilePicture: StoredFileDTO?,
    val karma: Int,
    @Contextual val createdAt: LocalDateTime,
    val spots: List<SpotDTO> = emptyList(),
    val posts: List<PostDTO> = emptyList(),
    val following: List<UserDTO> = emptyList(),
    val followers: List<UserDTO> = emptyList()

) : DTOBase() {

    override fun toEntity() = User(
        id = null,
        uuid = uuid,
        username = username,
        email = email,
        profilePicture = profilePicture?.toEntity(),
        karma = karma,
        createdAt = createdAt,
    )

}
