package iut.fauryollivier.snoozespot.api.repositories

import PostCommentRepository
import iut.fauryollivier.snoozespot.api.database.Tables
import iut.fauryollivier.snoozespot.api.database.Tables.PostComments.postId
import iut.fauryollivier.snoozespot.api.database.selectVisible
import iut.fauryollivier.snoozespot.api.entities.Post
import iut.fauryollivier.snoozespot.api.entities.PostComment
import iut.fauryollivier.snoozespot.api.entities.StoredFile
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class PostRepository(private val userRepository: UserRepository) : RepositoryBase() {

    override fun ResultRow.toEntity(
        loadRelations: Boolean
    ): Post {
        val pictures = if (loadRelations) emptyList<StoredFile>() else emptyList<StoredFile>() //TODO: load pictures
        val comments = if (loadRelations) emptyList<PostComment>() else emptyList<PostComment>()
        val likeCount = getLikeCount(this[Tables.Posts.id].value).getOrThrow()

        return Post(
            id = this[Tables.Posts.id].value,
            user = userRepository.getById(this[Tables.Posts.userId]).getOrThrow(),
            content = this[Tables.Posts.content],
            likeCount = likeCount,
            createdAt = this[Tables.Posts.createdAt],
            deletedAt = this[Tables.Posts.deletedAt],
            pictures = pictures,
            comments = comments
        )
    }

    fun getAll(from: Int = -1, to: Int = -1): Result<List<Post>> {
        if (from > to) return Result.failure(Exception("From must be before to"))
        val list = transaction {
            var query = Tables.Posts.selectVisible().orderBy(Tables.Posts.createdAt, SortOrder.DESC)

            if (from >= 0 && to >= 0 && to >= from) {
                query = query.limit(to - from + 1, from.toLong())
            }

            query.map {
                it.toEntity(loadRelations = false)
            }
        }
        return Result.success(list)
    }

    fun getById(id: Int): Result<Post> {
        val post = transaction {
            Tables.Posts.select { Tables.Posts.id eq id }.selectVisible().map { it ->
                it.toEntity(true)
            }.firstOrNull()
        }
        if(post == null) return Result.failure(Exception("Post not found"))
        return Result.success(post)
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

    fun getUserPosts(userId: Int): Result<List<Post>> {
        val posts = transaction {
            Tables.Posts.select { Tables.Posts.userId eq userId }.selectVisible().map { it ->
                it.toEntity(false)
            }
        }
        return Result.success(posts)
    }

    fun getLikeCount(postId: Int): Result<Int> {
        val likeCount = Tables.PostLikes
            .select { Tables.PostLikes.postId eq postId }
            .count()
        return Result.success(likeCount.toInt())
    }
}