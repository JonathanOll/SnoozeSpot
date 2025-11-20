package iut.fauryollivier.snoozespot.api.services

import iut.fauryollivier.snoozespot.api.dtos.SpotDTO
import iut.fauryollivier.snoozespot.api.repositories.SpotRepository

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
}
