import iut.fauryollivier.snoozespot.api.database.Tables
import iut.fauryollivier.snoozespot.api.entities.PostComment
import iut.fauryollivier.snoozespot.api.model.PostCommentModel
import iut.fauryollivier.snoozespot.api.repositories.RepositoryBase
import iut.fauryollivier.snoozespot.api.repositories.UserRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class PostCommentRepository(private val userRepository: UserRepository) : RepositoryBase() {

    override fun ResultRow.toEntity() = PostComment(
        id = this[Tables.PostComments.id].value,
        userId = this[Tables.PostComments.userId],
        content = this[Tables.PostComments.content],
        createdAt = this[Tables.PostComments.createdAt],
        deletedAt = this[Tables.PostComments.deletedAt],
    )

    fun getByPostId(postId: Int): Result<List<PostCommentModel>> {
        val comments = transaction {
            Tables.PostComments
                .select { Tables.PostComments.postId eq postId }
                .orderBy(Tables.SpotComments.createdAt, SortOrder.ASC)
                .map { row ->
                    row.toEntity()
                }
        }
        return Result.success(comments.map { it.toModel() })
    }
}