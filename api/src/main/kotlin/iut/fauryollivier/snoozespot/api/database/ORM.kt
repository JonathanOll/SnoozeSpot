package iut.fauryollivier.snoozespot.api.database

import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction
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

        for (i in 1..100)
            Tables.Posts.insert {
                it[this.userId] = user.value
                it[content] = "This is a sample post content [$i]"
                it[likeCount] = 123
                it[createdAt] = LocalDateTime.now().minusDays(3)
                it[deletedAt] = null
            }

    }
}

fun Application.configureORM() {
    Database.connect("jdbc:sqlite:../database/database.db", driver = "org.sqlite.JDBC")

    resetDatabase()
    insertDefault()

    println("Database initialized")
}