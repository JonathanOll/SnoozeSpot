package iut.fauryollivier.snoozespot.api.services

import iut.fauryollivier.snoozespot.api.dtos.UserDTO
import iut.fauryollivier.snoozespot.api.repositories.PostRepository
import iut.fauryollivier.snoozespot.api.repositories.UserRepository
import java.util.*

class UserService(private val userRepository: UserRepository, private val postRepository: PostRepository) {

    fun getAll(): Result<List<UserDTO>> {
        val result = userRepository.getAll()
        if (result.isFailure) return Result.failure(result.exceptionOrNull()!!)
        return Result.success(result.getOrThrow().map { it.toDTO() })
    }

    fun getByUuid(uuid: UUID): Result<UserDTO> {
        val id = userRepository.getUserIdByUUID(uuid)
        if (id.isFailure) return Result.failure(Exception("unknown UUID $uuid"))

        val result = userRepository.getById(id.getOrThrow(), true)
        if (result.isFailure) return Result.failure(result.exceptionOrNull()!!)

        var user = result.getOrThrow()

        val posts = postRepository.getUserPosts(id.getOrThrow())
        if (posts.isSuccess)
            user = user.copy(
                posts = posts.getOrThrow()
            )

        return Result.success(user.toDTO())
    }

    fun getById(id: Int): Result<UserDTO> {
        val result = userRepository.getById(id, true)
        if (result.isFailure)
            return Result.failure(result.exceptionOrNull()!!)

        return Result.success(result.getOrThrow().toDTO())
    }

    fun changeProfilePicture(userId: Int, fileId: Int): Result<UserDTO> {
        val result = userRepository.changeProfilePicture(userId, fileId)
        if (result.isFailure)
            return Result.failure(result.exceptionOrNull()!!)

        val user = userRepository.getById(userId, true)
        if (result.isFailure)
            return Result.failure(result.exceptionOrNull()!!)

        return Result.success(user.getOrThrow().toDTO());
    }
}