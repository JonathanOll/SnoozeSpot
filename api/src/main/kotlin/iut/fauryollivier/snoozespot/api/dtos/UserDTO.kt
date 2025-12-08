package iut.fauryollivier.snoozespot.api.dtos

import iut.fauryollivier.snoozespot.api.model.UserModel
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import java.util.UUID

@Serializable
data class UserDTO(

    @Contextual val uuid: UUID,
    val username: String,
    val email: String?,
    val profilePicture: StoredFileDTO?,
    val karma: Int,
    @Contextual val createdAt: LocalDateTime,
    val spots: List<SpotDTO>? = emptyList(),
    val posts: List<PostDTO>? = emptyList(),
    val following: List<UserDTO>? = emptyList(),
    val followers: List<UserDTO>? = emptyList()

) : DTOBase() {

    override fun toModel() = UserModel(
        id = null,
        uuid = uuid,
        username = username,
        email = email,
        profilePictureId = null,
        karma = karma,
        canConnect = false,
        createdAt = createdAt,
        deletedAt = null,
        spotsIds = emptyList(),
        postsIds = emptyList(),
        followingIds = emptyList(),
        followersIds = emptyList(),

        profilePicture = profilePicture?.toModel(),
        spots = null, //spots?.map { it.toModel() },
        posts = null, //posts?.map { it.toModel() },
        following = emptyList(),
        followers = emptyList()
    )

}
