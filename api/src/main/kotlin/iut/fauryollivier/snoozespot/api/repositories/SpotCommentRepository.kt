import iut.fauryollivier.snoozespot.api.database.Tables
import iut.fauryollivier.snoozespot.api.entities.SpotComment
import iut.fauryollivier.snoozespot.api.repositories.RepositoryBase
import iut.fauryollivier.snoozespot.api.repositories.UserRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

class SpotCommentRepository(private val userRepository: UserRepository) : RepositoryBase() {

    override fun ResultRow.toEntity(
        loadRelations: Boolean
    ): SpotComment {

        return SpotComment(
            id = this[Tables.SpotComments.id],
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
}