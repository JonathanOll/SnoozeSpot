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

    fun getByUuid(uuid: UUID, authUser: Int?): Result<UserDTO> {
        val id = userRepository.getUserIdByUUID(uuid)
        if (id.isFailure) return Result.failure(Exception("unknown UUID $uuid"))

        val result = userRepository.getById(id.getOrThrow(), authUser, true)
        if (result.isFailure) return Result.failure(result.exceptionOrNull()!!)

        var user = result.getOrThrow()

        val posts = postRepository.getUserPosts(id.getOrThrow())
        if (posts.isSuccess)
            user = user.copy(
                posts = posts.getOrThrow()
            )

        return Result.success(user.toDTO())
    }

    fun getUserIdByUUID(uuid: UUID): Result<Int> {
        val result = userRepository.getUserIdByUUID(uuid)
        if (result.isFailure)
            return Result.failure(result.exceptionOrNull()!!)

        return Result.success(result.getOrThrow())
    }

    fun getById(id: Int): Result<UserDTO> {
        val result = userRepository.getById(id, loadRelations = true)
        if (result.isFailure)
            return Result.failure(result.exceptionOrNull()!!)

        return Result.success(result.getOrThrow().toDTO())
    }

    fun changeProfilePicture(userId: Int, fileId: Int): Result<UserDTO> {
        val result = userRepository.changeProfilePicture(userId, fileId)
        if (result.isFailure)
            return Result.failure(result.exceptionOrNull()!!)

        val user = userRepository.getById(userId, loadRelations = true)
        if (result.isFailure)
            return Result.failure(result.exceptionOrNull()!!)

        return Result.success(user.getOrThrow().toDTO());
    }

    fun followUser(followerId: Int, followedId: Int): Result<Unit> {
        if (followerId == followedId)
            return Result.failure(Exception("You cannot follow yourself"))

        val result = userRepository.follow(followerId, followedId)
        if (result.isFailure)
            return Result.failure(result.exceptionOrNull()!!)

        return Result.success(Unit)
    }

    fun unfollowUser(followerId: Int, followedId: Int): Result<Unit> {
        if (followerId == followedId)
            return Result.failure(Exception("You cannot unfollow yourself"))

        val result = userRepository.unfollow(followerId, followedId)
        if (result.isFailure)
            return Result.failure(result.exceptionOrNull()!!)

        return Result.success(Unit)
    }

    fun getFollowing(userId: Int): Result<List<UserDTO>> {
        val result = userRepository.getFollowing(userId)
        if (result.isFailure)
            return Result.failure(result.exceptionOrNull()!!)

        return Result.success(result.getOrThrow().map { it.toDTO() })
    }

    fun getByEmail(email: String): Result<UserDTO> {
        val userResult = userRepository.getByEmail(email)
        if (userResult.isFailure) {
            return Result.failure(userResult.exceptionOrNull()!!)
        }

        val user = userResult.getOrThrow()
        return Result.success(user.toDTO())
    }

    fun changeProfilePicture(uuid: UUID, fileId: Int): Result<UserDTO> {
        val userIdResult = userRepository.getUserIdByUUID(uuid)
        if (userIdResult.isFailure) {
            return Result.failure(Exception("Unknown UUID $uuid"))
        }
        val userId = userIdResult.getOrThrow()

        val updateResult = userRepository.changeProfilePicture(userId, fileId)
        if (updateResult.isFailure) {
            return Result.failure(updateResult.exceptionOrNull()!!)
        }

        val userResult = userRepository.getById(userId, loadRelations = true)
        if (userResult.isFailure) {
            return Result.failure(userResult.exceptionOrNull()!!)
        }

        return Result.success(userResult.getOrThrow().toDTO())
    }

}