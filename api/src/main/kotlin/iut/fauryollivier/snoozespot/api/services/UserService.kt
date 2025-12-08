package iut.fauryollivier.snoozespot.api.services

import iut.fauryollivier.snoozespot.api.models.UserModel
import iut.fauryollivier.snoozespot.api.repositories.UserRepository
import java.util.UUID

class UserService(private val userRepository: UserRepository) {

    fun getAll(): Result<List<UserModel>> {
        val result = userRepository.getAll()
        if (result.isFailure) return Result.failure(result.exceptionOrNull()!!)
        return Result.success(result.getOrThrow())
    }

    fun getByUuid(uuid: UUID): Result<UserModel> {
        val id = userRepository.getUserIdByUUID(uuid);
        if(id.isFailure) return Result.failure(Exception("unknown UUID $uuid"))

        val result = userRepository.getById(id.getOrThrow(), true)
        if (result.isFailure) return Result.failure(result.exceptionOrNull()!!)

        return Result.success(result.getOrThrow())
    }
}