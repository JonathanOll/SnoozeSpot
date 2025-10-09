package iut.fauryollivier.snoozespot.api.repositories

import iut.fauryollivier.snoozespot.api.database.Tables
import iut.fauryollivier.snoozespot.api.models.User
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepository {
    fun getAll(): List<User> = transaction {
        Tables.Users.selectAll().map {
            User(
                id = it[Tables.Users.id].value,
                username = it[Tables.Users.username],
                email = it[Tables.Users.email],
                profilePicture = null,
                karma = it[Tables.Users.karma],
                createdAt = it[Tables.Users.createdAt]
            )
        }
    }

    fun getById(id: Int) = transaction {
        Tables.Users.select { Tables.Users.id eq id }.map {
            User(
                id = it[Tables.Users.id].value,
                username = it[Tables.Users.username],
                email = it[Tables.Users.email],
                profilePicture = null,
                karma = it[Tables.Users.karma],
                createdAt = it[Tables.Users.createdAt]
            )
        }
    }.firstOrNull()
}