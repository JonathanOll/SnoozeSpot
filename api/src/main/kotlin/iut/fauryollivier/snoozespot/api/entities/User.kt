package iut.fauryollivier.snoozespot.api.entities

import iut.fauryollivier.snoozespot.api.models.UserModel
import java.time.LocalDateTime
import java.util.*


data class User(

    val id: Int?,
    val uuid: UUID,
    val username: String,
    val email: String?,
    val profilePicture: Int?,
    val karma: Int,
    val canConnect: Boolean,
    val createdAt: LocalDateTime,
    val deletedAt: LocalDateTime?,

    val spots: List<Int>,
    val posts: List<Int>,
    val following: List<Int>,
    val followers: List<Int>

) : EntityBase() {

    override fun toModel(): UserModel = UserModel(
        id = id,
        uuid = uuid,
        username = username,
        email = email,
        profilePictureId = profilePicture,
        karma = karma,
        canConnect = canConnect,
        createdAt = createdAt,
        deletedAt = deletedAt,
        spotsIds = spots,
        postsIds = posts,
        followingIds = following,
        followersIds = followers,
    )
}