import iut.fauryollivier.snoozespot.api.database.Tables
import iut.fauryollivier.snoozespot.api.database.selectVisible
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

class SpotCommentRepository(private val userRepository: UserRepository) : RepositoryBase() {

    override fun ResultRow.toEntity(
        loadRelations: Boolean
    ): SpotComment {

        return SpotComment(
            id = this[Tables.SpotComments.id].value,
            user = userRepository.getById(this[Tables.SpotComments.userId]).getOrThrow(),
            description = this[Tables.SpotComments.description],
            rating = this[Tables.SpotComments.rating],
            createdAt = this[Tables.SpotComments.createdAt],
            deletedAt = this[Tables.SpotComments.deletedAt]
        )
    }

    fun getBySpotId(spotId: Int): Result<List<SpotComment>> {
        val comments = transaction {
            Tables.SpotComments
                .select { Tables.SpotComments.spotId eq spotId }
                .orderBy(Tables.SpotComments.createdAt, SortOrder.ASC)
                .map { row ->
                    row.toEntity(loadRelations = false)
                }
        }
        return Result.success(comments)
    }

    fun createSpotComment(userId: Int, spotId: Int, content: String, rating: Int): Result<Int> {
        val id = transaction {
            Tables.SpotComments.insertAndGetId {
                it[this.spotId] = spotId
                it[this.userId] = userId
                it[this.description] = content
                it[this.rating] = rating
            }
        }
        return Result.success(id.value)
    }

    fun getById(id: Int): Result<SpotComment> {
        val spotComment = transaction {
            Tables.SpotComments.select { Tables.SpotComments.id eq id }.selectVisible().map { it ->
                it.toEntity(true)
            }.firstOrNull()
        }
        if(spotComment == null) return Result.failure(Exception("Post not found"))
        return Result.success(spotComment)
    }
}