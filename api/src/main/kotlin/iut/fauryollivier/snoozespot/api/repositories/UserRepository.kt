package iut.fauryollivier.snoozespot.api.repositories

import iut.fauryollivier.snoozespot.api.auth.Password
import iut.fauryollivier.snoozespot.api.auth.model.UserAuthRequest
import iut.fauryollivier.snoozespot.api.database.Tables
import iut.fauryollivier.snoozespot.api.database.selectActive
import iut.fauryollivier.snoozespot.api.entities.User
import iut.fauryollivier.snoozespot.api.models.UserModel
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class UserRepository : RepositoryBase() {

    override fun ResultRow.toEntity(): User {

        return User(
            id = this[Tables.Users.id].value,
            uuid = this[Tables.Users.uuid],
            username = this[Tables.Users.username],
            email = this[Tables.Users.email],
            profilePicture = this[Tables.Users.profilePictureId],
            karma = this[Tables.Users.karma],
            canConnect = this[Tables.Users.canConnect] == 1,
            createdAt = this[Tables.Users.createdAt],
            deletedAt = this[Tables.Users.deletedAt],

            spots = emptyList(),
            posts = emptyList(),
            following = emptyList(),
            followers = emptyList(),
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

    fun getAll(): Result<List<UserModel>> {
        val res = transaction {
            Tables.Users.selectAll().map {
                it.toEntity()
            }
        }
        return Result.success(res.map { it.toModel() })
    }

    fun getById(id: Int, loadRelations: Boolean = false) : Result<UserModel> {
        val user = transaction {
            Tables.Users.select { Tables.Users.id eq id }.map {
                it.toEntity()
            }
        }.firstOrNull()
        if(user == null) return Result.failure(Exception("User not found"))
        return Result.success(user.toModel())
    }

    fun getByUsername(username: String) : Result<UserModel> {
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