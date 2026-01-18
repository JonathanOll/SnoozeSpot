package iut.fauryollivier.snoozespot.api.repositories

import iut.fauryollivier.snoozespot.api.auth.Password
import iut.fauryollivier.snoozespot.api.auth.model.UserAuthRequest
import iut.fauryollivier.snoozespot.api.database.Tables
import iut.fauryollivier.snoozespot.api.database.selectActive
import iut.fauryollivier.snoozespot.api.entities.EntityBase
import iut.fauryollivier.snoozespot.api.entities.Post
import iut.fauryollivier.snoozespot.api.entities.Spot
import iut.fauryollivier.snoozespot.api.entities.StoredFile
import iut.fauryollivier.snoozespot.api.entities.User
import iut.fauryollivier.snoozespot.api.enums.StoredFileType
import iut.fauryollivier.snoozespot.api.enums.StoredFileUsage
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class UserRepository : RepositoryBase() {
    override fun ResultRow.toEntity(loadRelations: Boolean): User {
        return toEntity(loadRelations, null)
    }

    fun ResultRow.toEntity(
        loadRelations: Boolean,
        authUser: Int?
    ): User {
        val spots = if (loadRelations) emptyList() else emptyList<Spot>() //TODO: load spots
        val posts = if (loadRelations) emptyList() else emptyList<Post>() //TODO: load posts
        val following = if (loadRelations) emptyList() else emptyList<User>() //TODO: load following
        val followers = if (loadRelations) emptyList() else emptyList<User>() //TODO

        val followedByUser = authUser != null && isFollowedBy(this[Tables.Users.id].value, authUser).getOrThrow()

        val profilePictureId = this[Tables.Users.profilePictureId]

        val profilePicture = if (profilePictureId != null) {
            val fileRow = Tables.Files
                .select { Tables.Files.id eq profilePictureId }
                .singleOrNull()

            fileRow?.let {
                StoredFile(
                    id = it[Tables.Files.id].value,
                    uuid = it[Tables.Files.uuid],
                    description = it[Tables.Files.description],
                    path = it[Tables.Files.path],
                    type = StoredFileType.fromInt(it[Tables.Files.type]) ?: StoredFileType.UNKNOWN,
                    usage = StoredFileUsage.fromInt(it[Tables.Files.usage]) ?: StoredFileUsage.UNKNOW,
                    createdAt = it[Tables.Files.createdAt],
                    deletedAt = it[Tables.Files.deletedAt]
                )
            }
        } else null

        return User(
            id = this[Tables.Users.id].value,
            uuid = this[Tables.Users.uuid],
            username = this[Tables.Users.username],
            email = this[Tables.Users.email],
            profilePicture = profilePicture,
            karma = this[Tables.Users.karma],
            createdAt = this[Tables.Users.createdAt],
            deletedAt = this[Tables.Users.deletedAt],
            spots = spots,
            posts = posts,
            following = following,
            followers = followers,
            followedByUser = followedByUser
        )
    }

    fun create(user: UserAuthRequest): Result<Int> {
        val id = transaction {
            Tables.Users.insertAndGetId {
                it[email] = user.email
                it[username] = user.username
                it[password] = Password.hash(user.password)
                it[roleId] = 0
            }
        }
        return Result.success(id.value)
    }

    fun getUserIdByUUID(uuid: UUID): Result<Int> {
        val id = transaction {
            Tables.Users
                .select { Tables.Users.uuid eq uuid }
                .selectActive()
                .map { it[Tables.Users.id] }
                .firstOrNull()
        }
        if (id?.value == null) {
            return Result.failure(Exception("User not found"))
        }
        return Result.success(id.value)

    }

    fun getUsernamePasswordHash(username: String): Result<String> {
        val password = transaction {
            Tables.Users
                .select { Tables.Users.username eq username }
                .selectActive()
                .map { it[Tables.Users.password] }
                .firstOrNull()
        }
        if (password == null) {
            return Result.failure(Exception("User not found"))
        }
        return Result.success(password)
    }

    fun getAll(): Result<List<User>> {
        val res = transaction {
            Tables.Users.selectAll().map {
                it.toEntity(loadRelations = false)
            }
        }
        return Result.success(res)
    }

    fun getById(id: Int, authUser: Int? = null, loadRelations: Boolean = false): Result<User> {
        val user = transaction {
            Tables.Users.select { Tables.Users.id eq id }.map {
                it.toEntity(authUser = authUser, loadRelations = loadRelations)
            }
        }.firstOrNull()
        if (user == null) return Result.failure(Exception("User not found"))
        return Result.success(user)
    }

    fun getByUsername(username: String): Result<User> {
        val id = transaction {
            Tables.Users
                .select { Tables.Users.username eq username }
                .selectActive()
                .map { it[Tables.Users.id] }
                .firstOrNull()
        }
        if (id == null) return Result.failure(Exception("User not found"))
        return getById(id.value)
    }

    fun changeProfilePicture(userId: Int, fileId: Int): Result<Unit> {
        val updatedRows = transaction {
            Tables.Users.update({ Tables.Users.id eq userId }) {
                it[profilePictureId] = fileId
            }
        }

        return if (updatedRows > 0) {
            Result.success(Unit)
        } else {
            Result.failure(Exception("User not found or profile picture unchanged"))
        }
    }

    fun follow(followerId: Int, followedId: Int): Result<Unit> {
        return try {
            transaction {
                Tables.Following.insert {
                    it[Tables.Following.followerId] = followerId
                    it[Tables.Following.followedId] = followedId
                }
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun unfollow(followerId: Int, followedId: Int): Result<Unit> {
        return try {
            transaction {
                Tables.Following.deleteWhere {
                    (Tables.Following.followerId eq followerId) and
                            (Tables.Following.followedId eq followedId)
                }
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getFollowing(userId: Int): Result<List<User>> {
        return try {
            val users = transaction {

                val followedIds = Tables.Following
                    .slice(Tables.Following.followedId)
                    .select { Tables.Following.followerId eq userId }
                    .map { it[Tables.Following.followedId] }

                if (followedIds.isEmpty()) {
                    return@transaction emptyList<User>()
                }

                Tables.Users
                    .select { Tables.Users.id inList followedIds }
                    .map { it.toEntity(loadRelations = false) }
            }

            Result.success(users)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    fun isFollowedBy(followedId: Int, followerId: Int): Result<Boolean> {
        val isFollowing = transaction {
            Tables.Following
                .select {
                    (Tables.Following.followedId eq followedId) and
                            (Tables.Following.followerId eq followerId)
                }
                .any()
        }
        return Result.success(isFollowing)
    }

    fun getByEmail(email: String): Result<User> {
        val id = transaction {
            Tables.Users
                .select { Tables.Users.email eq email }
                .selectActive()
                .map { it[Tables.Users.id] }
                .firstOrNull()
        }

        if (id == null) return Result.failure(Exception("User not found"))
        return getById(id.value)
    }


}