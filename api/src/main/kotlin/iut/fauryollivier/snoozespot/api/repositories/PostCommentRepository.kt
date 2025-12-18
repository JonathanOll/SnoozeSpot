import iut.fauryollivier.snoozespot.api.database.Tables
import iut.fauryollivier.snoozespot.api.database.selectVisible
import iut.fauryollivier.snoozespot.api.entities.Post
import iut.fauryollivier.snoozespot.api.entities.PostComment
import iut.fauryollivier.snoozespot.api.entities.SpotComment
import iut.fauryollivier.snoozespot.api.repositories.RepositoryBase
import iut.fauryollivier.snoozespot.api.repositories.UserRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

class PostCommentRepository(private val userRepository: UserRepository) : RepositoryBase() {

    override fun ResultRow.toEntity(
        loadRelations: Boolean
    ): PostComment {

        return PostComment(
            id = this[Tables.PostComments.id].value,
            user = userRepository.getById(this[Tables.PostComments.userId]).getOrThrow(),
            content = this[Tables.PostComments.content],
            createdAt = this[Tables.PostComments.createdAt],
            deletedAt = this[Tables.PostComments.deletedAt]
        )
    }

    fun getByPostId(postId: Int): Result<List<PostComment>> {
        val comments = transaction {
            Tables.PostComments
                .select { Tables.PostComments.postId eq postId }
                .orderBy(Tables.SpotComments.createdAt, SortOrder.ASC)
                .map { row ->
                    row.toEntity(loadRelations = false)
                }
        }
        return Result.success(comments)
    }

    fun getById(id: Int): Result<PostComment> {
        val postComment = transaction {
            Tables.PostComments.select { Tables.PostComments.id eq id }.selectVisible().map { it ->
                it.toEntity(true)
            }.firstOrNull()
        }
        if(postComment == null) return Result.failure(Exception("Post not found"))
        return Result.success(postComment)
    }

    fun createPostComment(userId: Int, postId: Int, content: String): Result<Int> {
        val id = transaction {
            Tables.PostComments.insertAndGetId {
                it[this.postId] = postId
                it[this.userId] = userId
                it[this.content] = content
            }
        }
        return Result.success(id.value)
    }
}