package iut.fauryollivier.snoozespot.api.dtos

import iut.fauryollivier.snoozespot.api.models.UserModel
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

    override fun toModel(loadRelations: Boolean) : UserModel = UserModel(
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

        profilePicture = if(loadRelations) profilePicture?.toModel(false) else null,
        spots = if(loadRelations) spots?.map { it.toModel(false) } else null,
        posts = if(loadRelations) posts?.map { it.toModel(false) } else null,
        following = if(loadRelations) following?.map { it.toModel(false) } else null,
        followers = if(loadRelations) followers?.map { it.toModel(false) } else null
    )

}
