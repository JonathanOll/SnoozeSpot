package iut.fauryollivier.snoozespot.api.repositories

import iut.fauryollivier.snoozespot.api.database.Tables
import iut.fauryollivier.snoozespot.api.models.Post
import iut.fauryollivier.snoozespot.api.models.User
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class PostRepository(private val userRepository: UserRepository) {


    fun getAll(): List<Post> = transaction {
        Tables.Posts.selectAll().map {
            Post(
                id = it[Tables.Posts.id],
                user = userRepository.getById(it[Tables.Posts.userId])!!,
                content = it[Tables.Posts.content],
                likeCount = it[Tables.Posts.likeCount],
                createdAt = it[Tables.Posts.createdAt],
            )
        }
    }

    fun getById(id: Int) = transaction {
        Tables.Posts.select { Tables.Posts.id eq  id }.map {
            Post(
                id = it[Tables.Posts.id],
                user = userRepository.getById(it[Tables.Posts.userId])!!,
                content = it[Tables.Posts.content],
                likeCount = it[Tables.Posts.likeCount],
                createdAt = it[Tables.Posts.createdAt],
            )
        }.firstOrNull()
    }
}