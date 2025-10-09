package iut.fauryollivier.snoozespot.api.repositories

import iut.fauryollivier.snoozespot.api.database.Tables
import iut.fauryollivier.snoozespot.api.models.User
import org.jetbrains.exposed.v1.jdbc.selectAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

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
}