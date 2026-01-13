package iut.fauryollivier.snoozespot.api.repositories

import iut.fauryollivier.snoozespot.api.database.Tables
import iut.fauryollivier.snoozespot.api.database.selectVisible
import iut.fauryollivier.snoozespot.api.entities.Post
import iut.fauryollivier.snoozespot.api.entities.PostComment
import iut.fauryollivier.snoozespot.api.entities.StoredFile
import iut.fauryollivier.snoozespot.api.entities.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class PostRepository(private val userRepository: UserRepository, private val storedFileRepository: StoredFileRepository) : RepositoryBase() {

    fun ResultRow.toEntity(
        loadRelations: Boolean,
        userId: Int?
    ): Post {
        val pictures = if (true || loadRelations) storedFileRepository.getFilesByPostId(this[Tables.Posts.id].value) else emptyList<StoredFile>() //TODO: load pictures
        val comments = if (loadRelations) emptyList<PostComment>() else emptyList<PostComment>()
        val likeCount = getLikeCount(this[Tables.Posts.id].value).getOrThrow()
        val likedByUser = userId != null && isLikedBy(this[Tables.Posts.id].value, userId).getOrThrow()

        return Post(
            id = this[Tables.Posts.id].value,
            user = userRepository.getById(this[Tables.Posts.userId]).getOrThrow(),
            content = this[Tables.Posts.content],
            likeCount = likeCount,
            createdAt = this[Tables.Posts.createdAt],
            deletedAt = this[Tables.Posts.deletedAt],
            pictures = pictures,
            comments = comments,
            likedByUser = likedByUser
        )
    }
    override fun ResultRow.toEntity(
        loadRelations: Boolean
    ): Post {
        return this.toEntity(loadRelations, null)
    }

    fun getAll(from: Int = -1, to: Int = -1, userId: Int?): Result<List<Post>> {
        if (from > to) return Result.failure(Exception("From must be before to"))
        val list = transaction {
            var query = Tables.Posts.selectVisible().orderBy(Tables.Posts.createdAt, SortOrder.DESC)

            if (from >= 0 && to >= 0 && to >= from) {
                query = query.limit(to - from + 1, from.toLong())
            }

            query.map {
                it.toEntity(loadRelations = false, userId)
            }
        }
        return Result.success(list)
    }

    fun getById(id: Int, userId: Int?): Result<Post> {
        val post = transaction {
            Tables.Posts.select { Tables.Posts.id eq id }.selectVisible().map { it ->
                it.toEntity(true, userId)
            }.firstOrNull()
        }
        if(post == null) return Result.failure(Exception("Post not found"))
        return Result.success(post)
    }


    fun createPost(userId: Int, content: String, files: List<Result<StoredFile>>): Result<Int> {
        val id = transaction {
            val id = Tables.Posts.insertAndGetId {
                it[this.userId] = userId
                it[this.content] = content
            }

            files.forEach { file->
                if (file.isSuccess) {
                    Tables.PostPictures.insert {
                        it[postId] = id.value
                        it[fileId] = file.getOrThrow().id!!
                    }
                }
            }
            id
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

    fun isLikedBy(postId: Int, userId: Int): Result<Boolean> {
        val liked = transaction {
            Tables.PostLikes
                .select { (Tables.PostLikes.postId eq postId) and (Tables.PostLikes.userId eq userId) }
                .any()
        }
        return Result.success(liked)
    }

    fun addLike(postId: Int, userId: Int): Result<Unit> {
        transaction {
            Tables.PostLikes.insert {
                it[Tables.PostLikes.postId] = postId
                it[Tables.PostLikes.userId] = userId
            }
        }
        return Result.success(Unit)
    }

    fun removeLike(postId: Int, userId: Int): Result<Unit> {
        transaction {
            Tables.PostLikes.deleteWhere {
                (Tables.PostLikes.postId eq postId) and (Tables.PostLikes.userId eq userId)
            }
        }
        return Result.success(Unit)
    }
}