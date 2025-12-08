package iut.fauryollivier.snoozespot.api.services

import iut.fauryollivier.snoozespot.api.dtos.SpotDTO
import iut.fauryollivier.snoozespot.api.entities.Spot
import iut.fauryollivier.snoozespot.api.repositories.SpotRepository
import iut.fauryollivier.snoozespot.api.routes.CreateSpotRequest
import org.koin.ktor.ext.inject
import kotlin.IllegalArgumentException

class SpotService(private val spotRepository: SpotRepository) {

    fun getAll(): Result<List<Spot>> {
        val result = spotRepository.getAll()
        if (result.isFailure) return Result.failure(result.exceptionOrNull()!!)
        return Result.success(result.getOrThrow())
    }

    fun getById(id: Int): Result<Spot> {
        val spotResult = spotRepository.getById(id)
        if (spotResult.isFailure) return Result.failure(Exception("Spot not found"))
        return Result.success(spotResult.getOrThrow())
    }

    fun getAllInZone(
        topLeftLatitude: Double,
        topLeftLongitude: Double,
        bottomRightLatitude: Double,
        bottomRightLongitude: Double
    ): Result<List<Spot>>{
        val result = spotRepository.getAllInZone(topLeftLatitude, topLeftLongitude, bottomRightLatitude, bottomRightLongitude)
        if (result.isFailure) return Result.failure(result.exceptionOrNull()!!)
        return Result.success(result.getOrThrow())
    }

    fun createSpot(userId: Int, data: CreateSpotRequest): Result<Spot> {
        val spotIdResult = spotRepository.createSpot(userId, data)
        if (spotIdResult.isFailure) return Result.failure(Exception("Spot could not be created"))

        val spotResult = getById(spotIdResult.getOrThrow())
        if (spotResult.isFailure) return Result.failure(Exception("Spot could not be created"))
        return Result.success(spotResult.getOrThrow())
    }
}
