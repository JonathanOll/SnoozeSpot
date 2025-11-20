package iut.fauryollivier.snoozespot.api.repositories

import SpotCommentRepository
import iut.fauryollivier.snoozespot.api.database.Tables
import iut.fauryollivier.snoozespot.api.database.Tables.SpotComments.rating
import iut.fauryollivier.snoozespot.api.database.selectVisible
import iut.fauryollivier.snoozespot.api.entities.Spot
import iut.fauryollivier.snoozespot.api.entities.SpotAttribute
import iut.fauryollivier.snoozespot.api.entities.SpotComment
import iut.fauryollivier.snoozespot.api.entities.StoredFile
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class SpotRepository(
    private val userRepository: UserRepository,
    private val spotCommentRepository: SpotCommentRepository

) : RepositoryBase() {

    override fun ResultRow.toEntity(
        loadRelations: Boolean
    ): Spot {
        val id = this[Tables.Spots.id].value

        val likeCount = getLikeCount(id).getOrThrow()
        val creator = if (loadRelations) userRepository.getById(this[Tables.Spots.creatorId]).getOrThrow() else null
        val pictures = if (loadRelations) emptyList() else emptyList<StoredFile>() //TODO: load pictures
        val attributes = if (loadRelations) emptyList() else emptyList<SpotAttribute>() //TODO: load attributes
        val comments = if (loadRelations) spotCommentRepository.getBySpotId(id).getOrThrow() else emptyList()

        val averageRating = comments.map { it.rating }.takeIf { it.isNotEmpty() }?.average()?.toFloat()

        return Spot(
            id = id,
            creator = creator,
            name = this[Tables.Spots.name],
            description = this[Tables.Spots.description],
            latitude = this[Tables.Spots.latitude],
            longitude = this[Tables.Spots.longitude],
            canBeDisplayed = this[Tables.Spots.canBeDisplayed],
            likeCount = likeCount,
            rating = averageRating,
            createdAt = this[Tables.Spots.createdAt],
            updatedAt = null,
            deletedAt = null,
            pictures = pictures,
            attributes = attributes,
            comments = comments
        )
    }

    fun getLikeCount(spotId: Int): Result<Int> {
        val likeCount = Tables.SpotLikes
            .select { Tables.SpotLikes.spotId eq spotId }
            .count()
        return Result.success(likeCount.toInt())
    }

    fun getAll(): Result<List<Spot>> {
        val list = transaction {
            val query = Tables.Spots.selectVisible().orderBy(Tables.Spots.createdAt, SortOrder.DESC)

            query.map {
                it.toEntity(loadRelations = true)
            }
        }
        return Result.success(list)
    }

    fun getById(id: Int): Result<Spot>{
        val spot = transaction {
            Tables.Spots.leftJoin(Tables.SpotComments)
                .select { Tables.Spots.id eq id }
                .selectVisible()
                .map { row ->
                    row.toEntity(loadRelations = true)
                }
                .firstOrNull()
        }
        if(spot == null) return Result.failure(Throwable("Spot not found"))
        return Result.success(spot)
    }

    fun getAllInZone(
        topLeftLatitude: Double,
        topLeftLongitude: Double,
        bottomRightLatitude: Double,
        bottomRightLongitude: Double,
    ): Result<List<Spot>> {
        val list = transaction {
            val query = Tables.Spots
                .select {
                    Tables.Spots.latitude greaterEq bottomRightLatitude and
                            (Tables.Spots.latitude lessEq topLeftLatitude) and
                            (Tables.Spots.longitude greaterEq topLeftLongitude) and
                            (Tables.Spots.longitude lessEq bottomRightLongitude)
                }
                .orderBy(Tables.Spots.createdAt, SortOrder.DESC)

            query.map {
                it.toEntity(loadRelations = true)
            }
        }
        return Result.success(list)
    }
}
