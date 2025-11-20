package iut.fauryollivier.snoozespot.api.repositories

import iut.fauryollivier.snoozespot.api.auth.Password
import iut.fauryollivier.snoozespot.api.auth.model.UserAuthRequest
import iut.fauryollivier.snoozespot.api.database.Tables
import iut.fauryollivier.snoozespot.api.database.selectActive
import iut.fauryollivier.snoozespot.api.entities.Post
import iut.fauryollivier.snoozespot.api.entities.Spot
import iut.fauryollivier.snoozespot.api.entities.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class UserRepository : RepositoryBase() {

    override fun ResultRow.toEntity(
        loadRelations: Boolean
    ): User {
        val spots = if (loadRelations)  emptyList() else emptyList<Spot>() //TODO: load spots
        val posts = if (loadRelations) emptyList() else emptyList<Post>() //TODO: load posts
        val following = if (loadRelations) emptyList() else emptyList<User>() //TODO: load following
        val followers = if (loadRelations) emptyList() else emptyList<User>() //TODO

        return User(
            uuid = this[Tables.Users.uuid],
            username = this[Tables.Users.username],
            email = this[Tables.Users.email],
            profilePicture = null,
            karma = this[Tables.Users.karma],
            createdAt = this[Tables.Users.createdAt],
            deletedAt = this[Tables.Users.deletedAt],
            spots = spots,
            posts = posts,
            following = following,
            followers = followers
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
        if(id?.value == null) {
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
        if(password == null) {
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

    fun getById(id: Int) : Result<User> {
        val user = transaction {
            Tables.Users.select { Tables.Users.id eq id }.map {
                it.toEntity(loadRelations = false)
            }
        }.firstOrNull()
        if(user == null) return Result.failure(Exception("User not found"))
        return Result.success(user)
    }

    fun getByUsername(username: String) : Result<User> {
        val id = transaction {
            Tables.Users
                .select { Tables.Users.username eq username }
                .selectActive()
                .map { it[Tables.Users.id] }
                .firstOrNull()
        }
        if(id == null) return Result.failure(Exception("User not found"))
        return getById(id.value)
    }
}