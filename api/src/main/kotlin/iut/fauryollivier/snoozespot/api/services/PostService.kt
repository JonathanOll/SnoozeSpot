package iut.fauryollivier.snoozespot.api.services

import iut.fauryollivier.snoozespot.api.model.PostModel
import iut.fauryollivier.snoozespot.api.repositories.PostRepository

class PostService(private val postRepository: PostRepository) {

    fun getAll(from: Int = -1, to: Int = -1): Result<List<PostModel>> {
        val result = postRepository.getAll(from, to)
        if (result.isFailure) return Result.failure(result.exceptionOrNull()!!)
        return Result.success(result.getOrThrow())
    }

    fun getById(id: Int): Result<PostModel> {
        val postResult = postRepository.getById(id)
        if (postResult.isFailure) return Result.failure(Exception("Post not found"))
        return Result.success(postResult.getOrThrow())
    }

    fun createPost(userId: Int, content: String): Result<PostModel> {
        val postIdResult = postRepository.createPost(userId, content)
        if (postIdResult.isFailure) return Result.failure(Exception("Post could not be created"))

        val postResult = postRepository.getById(postIdResult.getOrThrow())
        if (postResult.isFailure) return Result.failure(Exception("Post could not be created"))
        return Result.success(postResult.getOrThrow())
    }
}