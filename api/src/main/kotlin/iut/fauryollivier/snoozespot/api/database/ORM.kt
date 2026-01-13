package iut.fauryollivier.snoozespot.api.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
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

fun insertDefault() {
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

        for (i in 1..100) {
            val post = Tables.Posts.insertAndGetId {
                it[userId] = user.value
                it[content] = "This is a sample post content [$i]"
                it[createdAt] = LocalDateTime.now().minusDays(3)
                it[deletedAt] = null
            }

            for (j in 1..3)
                Tables.PostComments.insert {
                    it[userId] = user.value
                    it[postId] = post.value
                    it[content] = "This is a sample post comment [$j] for post [$i]"
                    it[createdAt] = LocalDateTime.now().minusDays(3)
                }

        }

        val spotId = Tables.Spots.insertAndGetId {
            it[creatorId] = 1
            it[name] = "Terrasse de N2JSoft"
            it[description] =
                "Une belle terrasse avec vue sur le lac, idéale pour des réunions ou des moments de détente."
            it[latitude] = 46.17048
            it[longitude] = 5.29052
            it[canBeDisplayed] = 1
        }.value

        Tables.SpotComments.insert {
            it[userId] = 1
            it[Tables.SpotComments.spotId] = spotId
            it[description] = "Super endroit pour bosser en plein air, calme et agréable."
            it[rating] = 5
        }

        Tables.SpotComments.insert {
            it[userId] = 1
            it[Tables.SpotComments.spotId] = spotId
            it[description] = "Vue magnifique, mais un peu trop de vent parfois."
            it[rating] = 4
        }

        Tables.SpotLikes.insert {
            it[userId] = 1
            it[Tables.SpotComments.spotId] = spotId
        }

        Tables.Spots.insert {
            it[creatorId] = 1
            it[name] = "Mairie de montcuq"
            it[description] = "trop marrant mdr."
            it[latitude] = 44.354128
            it[longitude] = 1.203491
            it[canBeDisplayed] = 1
        }


    }
}

fun Application.configureORM() {
    val config = HikariConfig().apply {
        jdbcUrl = "jdbc:sqlite:../database/database.db"
        driverClassName = "org.sqlite.JDBC"
        maximumPoolSize = 1
        isAutoCommit = false
        transactionIsolation = "TRANSACTION_SERIALIZABLE"
    }

    val dataSource = HikariDataSource(config)

    Database.connect(dataSource)

    resetDatabase()
    insertDefault()

    println("Database initialized")
}
