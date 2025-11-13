package iut.fauryollivier.snoozespot.api.repositories

import iut.fauryollivier.snoozespot.api.auth.Password
import iut.fauryollivier.snoozespot.api.auth.model.UserAuthRequest
import iut.fauryollivier.snoozespot.api.database.Tables
import iut.fauryollivier.snoozespot.api.database.selectActive
import iut.fauryollivier.snoozespot.api.models.User
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

class UserRepository {

    fun create(user: UserAuthRequest): Result<Int> {
        val id = transaction {
            Tables.Users.insertAndGetId {
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

    fun getAll(): List<User> = transaction {
        Tables.Users.selectAll().map {
            User(
                uuid = it[Tables.Users.uuid],
                username = it[Tables.Users.username],
                email = it[Tables.Users.email],
                profilePicture = null,
                karma = it[Tables.Users.karma],
                createdAt = it[Tables.Users.createdAt]
            )
        }
    }

    fun getById(id: Int) : Result<User> {
        val user = transaction {
            Tables.Users.select { Tables.Users.id eq id }.map {
                User(
                    uuid = it[Tables.Users.uuid],
                    username = it[Tables.Users.username],
                    email = it[Tables.Users.email],
                    profilePicture = null,
                    karma = it[Tables.Users.karma],
                    createdAt = it[Tables.Users.createdAt]
                )
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