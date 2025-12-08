package iut.fauryollivier.snoozespot.api.repositories

import iut.fauryollivier.snoozespot.api.database.Tables
import iut.fauryollivier.snoozespot.api.database.selectVisible
import iut.fauryollivier.snoozespot.api.entities.Post
import iut.fauryollivier.snoozespot.api.model.PostModel
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class PostRepository(private val userRepository: UserRepository) : RepositoryBase() {

    override fun ResultRow.toEntity(): Post {
        val id = this[Tables.Posts.id].value;

        return Post(
            id = this[Tables.Posts.id].value,
            userId = this[Tables.Posts.userId],
            content = this[Tables.Posts.content],
            canBeDisplayed = this[Tables.Posts.canBeDisplayed] == 1,
            createdAt = this[Tables.Posts.createdAt],
            deletedAt = this[Tables.Posts.deletedAt],
            pictures = emptyList(),
            comments = emptyList(),
            likeCount = getLikeCount(id).getOrThrow(),
        )
    }

    fun getAll(from: Int = -1, to: Int = -1): Result<List<PostModel>> {
        if (from > to) return Result.failure(Exception("From must be before to"))
        val list = transaction {
            var query = Tables.Posts.selectVisible().orderBy(Tables.Posts.createdAt, SortOrder.DESC)

            if (from >= 0 && to >= 0 && to >= from) {
                query = query.limit(to - from + 1, from.toLong())
            }

            query.map {
                it.toEntity()
            }
        }
        return Result.success(list.map { it.toModel() })
    }

    fun getById(id: Int): Result<PostModel> {
        val post = transaction {
            Tables.Posts.select { Tables.Posts.id eq id }.selectVisible().map { it ->
                it.toEntity()
            }.firstOrNull()
        }
        if(post == null) return Result.failure(Exception("Post not found"))
        return Result.success(post.toModel())
    }


    fun createPost(userId: Int, content: String): Result<Int> {
        val id = transaction {
            Tables.Posts.insertAndGetId {
                it[this.userId] = userId
                it[this.content] = content
            }
        }
        return Result.success(id.value)
    }

    fun getUserPosts(userId: Int): Result<List<PostModel>> {
        val posts = transaction {
            Tables.Posts.select { Tables.Posts.userId eq userId }.selectVisible().map { it ->
                it.toEntity()
            }
        }
        return Result.success(posts.map { it.toModel() })
    }

    fun getLikeCount(postId: Int): Result<Int> {
        val likeCount = Tables.PostLikes
            .select { Tables.PostLikes.postId eq postId }
            .count()
        return Result.success(likeCount.toInt())
    }
}