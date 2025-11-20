package iut.fauryollivier.snoozespot.api.services

import iut.fauryollivier.snoozespot.api.dtos.SpotDTO
import iut.fauryollivier.snoozespot.api.repositories.SpotRepository
import iut.fauryollivier.snoozespot.api.routes.CreateSpotRequest
import org.koin.ktor.ext.inject
import kotlin.IllegalArgumentException

class SpotService(private val spotRepository: SpotRepository) {

    fun getAll(): Result<List<SpotDTO>> {
        val result = spotRepository.getAll()
        if (result.isFailure) return Result.failure(result.exceptionOrNull()!!)
        return Result.success(result.getOrThrow().map { it.toDTO() })
    }

    fun getById(id: Int): Result<SpotDTO> {
        val spotResult = spotRepository.getById(id)
        if (spotResult.isFailure) return Result.failure(Exception("Spot not found"))
        return Result.success(spotResult.getOrThrow().toDTO())
    }

    fun getAllInZone(
        topLeftLatitude: Double,
        topLeftLongitude: Double,
        bottomRightLatitude: Double,
        bottomRightLongitude: Double
    ): Result<List<SpotDTO>> {
        val result = spotRepository.getAllInZone(topLeftLatitude, topLeftLongitude, bottomRightLatitude, bottomRightLongitude)
        if (result.isFailure) return Result.failure(result.exceptionOrNull()!!)
        return Result.success(result.getOrThrow().map { it.toDTO() })
    }

    fun createSpot(userId: Int, data: CreateSpotRequest): Result<SpotDTO> {
        val spotIdResult = spotRepository.createSpot(userId, data)
        if (spotIdResult.isFailure) return Result.failure(Exception("Spot could not be created"))

        val spotResult = getById(spotIdResult.getOrThrow())
        if (spotResult.isFailure) return Result.failure(Exception("Spot could not be created"))
        return Result.success(spotResult.getOrThrow())
    }
}
