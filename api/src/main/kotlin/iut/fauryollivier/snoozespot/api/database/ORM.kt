package iut.fauryollivier.snoozespot.api.database

import io.ktor.server.application.Application
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.insert
import org.jetbrains.exposed.v1.jdbc.insertAndGetId
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.time.LocalDateTime

fun resetDatabase() {
    transaction {
        SchemaUtils.drop(*Tables.all.toTypedArray())
        SchemaUtils.create(*Tables.all.toTypedArray())
    }
}

fun insertDefault () {
    transaction {
        val roleId = Tables.Roles.insertAndGetId {
            it[name] = "User"
        }

        val user = Tables.Users.insertAndGetId {
            it[id] = 1
            it[username] = "Template"
            it[email] = "template@gmail.com"
            it[password] = "password"
            it[this.roleId] = roleId.value
        }

        Tables.Posts.insert {
            it[userId] = user
            it[content] = "This is a sample post content"
            it[likeCount] = 123
            it[createdAt] = LocalDateTime.now().minusDays(3)
            it[deletedAt] = null
        }


    }
}

fun Application.configureORM() {
    Database.connect("jdbc:sqlite:../database/database.db", driver="org.h2.Driver")

    resetDatabase()
    insertDefault()

    println("Database initialized")
}