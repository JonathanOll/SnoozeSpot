package iut.fauryollivier.snoozespot.api.repositories

import iut.fauryollivier.snoozespot.api.database.Tables
import iut.fauryollivier.snoozespot.api.database.selectVisible
import iut.fauryollivier.snoozespot.api.models.Spot
import iut.fauryollivier.snoozespot.api.models.SpotComment
import iut.fauryollivier.snoozespot.api.models.User
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.SqlExpressionBuilder.greaterEq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class SpotRepository(private val userRepository: UserRepository) {

    fun getAll(): List<Spot> = transaction {
        val query = Tables.Spots.selectVisible().orderBy(Tables.Spots.createdAt, SortOrder.DESC)

        query.map {
            Spot(
                id = it[Tables.Spots.id],
                creator = userRepository.getById(it[Tables.Spots.creatorId]).getOrThrow(),
                name = it[Tables.Spots.name],
                description = it[Tables.Spots.description],
                latitude = it[Tables.Spots.latitude],
                longitude = it[Tables.Spots.longitude],
                createdAt = it[Tables.Spots.createdAt],
                likeCount = 0
            )
        }
    }

    fun getById(id: Int): Spot? = transaction {
        Tables.Spots.leftJoin(Tables.SpotComments)
            .select { Tables.Spots.id eq id }
            .selectVisible()
            .map { row ->
                Spot(
                    id = row[Tables.Spots.id],
                    creator = userRepository.getById(row[Tables.Spots.creatorId]).getOrThrow(),
                    name = row[Tables.Spots.name],
                    description = row[Tables.Spots.description],
                    latitude = row[Tables.Spots.latitude],
                    longitude = row[Tables.Spots.longitude],
                    createdAt = row[Tables.Spots.createdAt],
                    comments = Tables.SpotComments
                        .select { Tables.SpotComments.spotId eq id }
                        .map { commentRow ->
                            SpotComment(
                                id = commentRow[Tables.SpotComments.id],
                                user = userRepository.getById(commentRow[Tables.SpotComments.userId]).getOrThrow(),
                                description = commentRow[Tables.SpotComments.description],
                                rating = commentRow[Tables.SpotComments.rating],
                                createdAt = commentRow[Tables.SpotComments.createdAt]
                            )
                        },
                    likeCount = 0,
                )
            }
            .firstOrNull()
    }

    fun getAllInZone(
        topLeftLatitude: Double,
        topLeftLongitude: Double,
        bottomRightLatitude: Double,
        bottomRightLongitude: Double,
    ): List<Spot> = transaction {
        var query = Tables.Spots
            .select {
                Tables.Spots.latitude greaterEq bottomRightLatitude and
                        (Tables.Spots.latitude lessEq topLeftLatitude) and
                        (Tables.Spots.longitude greaterEq topLeftLongitude) and
                        (Tables.Spots.longitude lessEq bottomRightLongitude)
            }
            .orderBy(Tables.Spots.createdAt, SortOrder.DESC)

        query.map {
            Spot(
                id = it[Tables.Spots.id],
                creator = userRepository.getById(it[Tables.Spots.creatorId]).getOrThrow(),
                name = it[Tables.Spots.name],
                description = it[Tables.Spots.description],
                latitude = it[Tables.Spots.latitude],
                longitude = it[Tables.Spots.longitude],
                createdAt = it[Tables.Spots.createdAt],
                likeCount = 0
            )
        }
    }
}
