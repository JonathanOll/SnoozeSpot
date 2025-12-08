package iut.fauryollivier.snoozespot.api.repositories

import SpotCommentRepository
import iut.fauryollivier.snoozespot.api.database.Tables
import iut.fauryollivier.snoozespot.api.database.selectVisible
import iut.fauryollivier.snoozespot.api.entities.Spot
import iut.fauryollivier.snoozespot.api.model.SpotModel
import iut.fauryollivier.snoozespot.api.routes.CreateSpotRequest
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class SpotRepository(
    private val userRepository: UserRepository,
    private val spotCommentRepository: SpotCommentRepository
) : RepositoryBase() {

    override fun ResultRow.toEntity(): Spot {
        val id = this[Tables.Spots.id].value

        return Spot(
            id = id,
            creator = this[Tables.Spots.creatorId],
            name = this[Tables.Spots.name],
            description = this[Tables.Spots.description],
            latitude = this[Tables.Spots.latitude],
            longitude = this[Tables.Spots.longitude],
            canBeDisplayed = this[Tables.Spots.canBeDisplayed],
            likeCount = getLikeCount(id).getOrThrow(),
            rating = getRating(id).getOrNull(),
            createdAt = this[Tables.Spots.createdAt],
            deletedAt = this[Tables.Spots.deletedAt],

            pictures = emptyList(),
            attributes = emptyList(),
            comments = emptyList(),
        )
    }

    fun getAll(): Result<List<SpotModel>> {
        val list = transaction {
            val query = Tables.Spots.selectVisible().orderBy(Tables.Spots.createdAt, SortOrder.DESC)

            query.map {
                it.toEntity()
            }
        }
        return Result.success(list.map { it.toModel() })
    }

    fun getById(id: Int): Result<SpotModel>{
        val spot = transaction {
            Tables.Spots.leftJoin(Tables.SpotComments)
                .select { Tables.Spots.id eq id }
                .selectVisible()
                .map { row ->
                    row.toEntity()
                }
                .firstOrNull()
        }
        if(spot == null) return Result.failure(Throwable("Spot not found"))
        return Result.success(spot.toModel())
    }

    fun getAllInZone(
        topLeftLatitude: Double,
        topLeftLongitude: Double,
        bottomRightLatitude: Double,
        bottomRightLongitude: Double,
    ): Result<List<SpotModel>> {
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
                it.toEntity()
            }
        }
        return Result.success(list.map { it.toModel() })
    }

    fun createSpot(userId: Int, data: CreateSpotRequest): Result<Int> {
        val id = transaction {
            Tables.Spots.insertAndGetId {
                it[this.creatorId] = userId
                it[this.name] = data.name
                it[this.description] = data.description
                it[this.latitude] = data.latitude
                it[this.longitude] = data.longitude
            }
        }
        return Result.success(id.value)
    }

    fun getLikeCount(spotId: Int): Result<Int> {
        val likeCount = Tables.SpotLikes
            .select { Tables.SpotLikes.spotId eq spotId }
            .count()
        return Result.success(likeCount.toInt())
    }

    fun getRating(spotId: Int): Result<Float> {
        val ratings = Tables.SpotComments
            .select { Tables.SpotComments.spotId eq spotId }
            .map { it[Tables.SpotComments.rating] }

        if (ratings.isEmpty()) {
            return Result.success(0f)
        }

        val averageRating = ratings.average().toFloat()
        return Result.success(averageRating)
    }
}
