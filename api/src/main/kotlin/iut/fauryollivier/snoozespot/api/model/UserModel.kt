package iut.fauryollivier.snoozespot.api.model

import iut.fauryollivier.snoozespot.api.dtos.UserDTO
import iut.fauryollivier.snoozespot.api.entities.User
import java.time.LocalDateTime
import java.util.*

data class UserModel(

    val id: Int?,
    val uuid: UUID,
    val username: String,
    val email: String?,
    val profilePictureId: Int?,
    val karma: Int,
    val canConnect: Boolean,
    val createdAt: LocalDateTime,
    val deletedAt: LocalDateTime?,
    val spotsIds: List<Int>,
    val postsIds: List<Int>,
    val followingIds: List<Int>,
    val followersIds: List<Int>,

    val profilePicture: StoredFileModel? = null,
    val spots: List<SpotModel>? = null,
    val posts: List<PostModel>? = null,
    val following: List<UserModel>? = null,
    val followers: List<UserModel>? = null

) : ModelBase() {

    override fun toEntity(): User = User(
        id = id,
        uuid = uuid,
        username = username,
        email = email,
        profilePicture = profilePictureId,
        karma = karma,
        canConnect = canConnect,
        createdAt = createdAt,
        deletedAt = deletedAt,
        spots = spotsIds,
        posts = postsIds,
        following = followingIds,
        followers = followersIds
    )

    override fun toDTO(): UserDTO = UserDTO(
        uuid = uuid,
        username = username,
        email = email,
        profilePicture = profilePicture?.toDTO(),
        karma = karma,
        createdAt = createdAt,
        spots = spots?.map { it.toDTO() },
        posts = posts?.map { it.toDTO() },
        following = following?.map { it.toDTO() },
        followers = followers?.map { it.toDTO() }
    )

}