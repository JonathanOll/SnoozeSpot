package iut.fauryollivier.snoozespot.api.repositories

import iut.fauryollivier.snoozespot.api.database.Tables
import iut.fauryollivier.snoozespot.api.database.selectVisible
import iut.fauryollivier.snoozespot.api.models.Post
import iut.fauryollivier.snoozespot.api.models.PostComment
import iut.fauryollivier.snoozespot.api.models.User
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class PostRepository(private val userRepository: UserRepository) {


    fun getAll(from: Int = -1, to: Int = -1): List<Post> = transaction {
        var query = Tables.Posts.selectVisible().orderBy(Tables.Posts.createdAt, SortOrder.DESC)

        if(from >= 0 && to >= 0 && to >= from) {
            query = query.limit(to - from + 1, from.toLong())
        }

        query.map {
            Post(
                id = it[Tables.Posts.id].value,
                user = userRepository.getById(it[Tables.Posts.userId])!!,
                content = it[Tables.Posts.content],
                likeCount = it[Tables.Posts.likeCount],
                createdAt = it[Tables.Posts.createdAt],
            )
        }
    }

    fun getById(id: Int) = transaction {

        Tables.Posts.leftJoin(Tables.PostComments).select { Tables.Posts.id eq id }.selectVisible().map { it ->
            Post(
                id = it[Tables.Posts.id].value,
                user = userRepository.getById(it[Tables.Posts.userId])!!,
                content = it[Tables.Posts.content],
                likeCount = it[Tables.Posts.likeCount],
                createdAt = it[Tables.Posts.createdAt],
                comments = Tables.PostComments.select { Tables.PostComments.postId eq id }.map { it ->
                    PostComment(
                        id = it[Tables.PostComments.id].value,
                        content = it[Tables.PostComments.content],
                        createdAt = it[Tables.PostComments.createdAt],
                        user = userRepository.getById(it[Tables.PostComments.userId])!!
                    )
                }
            )
        }.firstOrNull()
    }

    fun createPost(userId: Int, content: String): Int {
        return transaction {
            Tables.Posts.insertAndGetId {
                it[this.userId] = userId
                it[this.content] = content
            }
        }.value
    }
}