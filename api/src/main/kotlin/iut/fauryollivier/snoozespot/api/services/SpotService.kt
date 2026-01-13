package iut.fauryollivier.snoozespot.api.services

import SpotCommentRepository
import iut.fauryollivier.snoozespot.api.dtos.SpotCommentDTO
import iut.fauryollivier.snoozespot.api.dtos.SpotDTO
import iut.fauryollivier.snoozespot.api.entities.StoredFile
import iut.fauryollivier.snoozespot.api.repositories.SpotRepository
import iut.fauryollivier.snoozespot.api.routes.CreateSpotRequest

class SpotService(
    private val spotRepository: SpotRepository,
    private val spotCommentRepository: SpotCommentRepository
) {

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
        val result =
            spotRepository.getAllInZone(topLeftLatitude, topLeftLongitude, bottomRightLatitude, bottomRightLongitude)
        if (result.isFailure) return Result.failure(result.exceptionOrNull()!!)
        return Result.success(result.getOrThrow().map { it.toDTO() })
    }

    fun createSpot(userId: Int, data: CreateSpotRequest, files: List<Result<StoredFile>>): Result<SpotDTO> {
        val spotIdResult = spotRepository.createSpot(userId, data, files)
        if (spotIdResult.isFailure) return Result.failure(Exception("Spot could not be created"))

        val spotResult = getById(spotIdResult.getOrThrow())
        if (spotResult.isFailure) return Result.failure(Exception("Spot could not be created"))
        return Result.success(spotResult.getOrThrow())
    }

    fun createSpotComment(userId: Int, spotId: Int, content: String, rating: Int): Result<SpotCommentDTO> {
        val spotCommentIdResult = spotCommentRepository.createSpotComment(userId, spotId, content, rating)
        if (spotCommentIdResult.isFailure) return Result.failure(Exception("Spot comment could not be created"))

        val spotResult = spotCommentRepository.getById(spotCommentIdResult.getOrThrow())
        if (spotResult.isFailure) return Result.failure(Exception("Spot comment could not be created"))
        return Result.success(spotResult.getOrThrow().toDTO())
    }
}
