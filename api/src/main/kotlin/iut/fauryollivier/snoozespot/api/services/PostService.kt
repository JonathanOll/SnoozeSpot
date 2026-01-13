package iut.fauryollivier.snoozespot.api.services

import PostCommentRepository
import iut.fauryollivier.snoozespot.api.dtos.PostCommentDTO
import iut.fauryollivier.snoozespot.api.dtos.PostDTO
import iut.fauryollivier.snoozespot.api.entities.StoredFile
import iut.fauryollivier.snoozespot.api.repositories.PostRepository

class PostService(
    private val postRepository: PostRepository,
    private val postCommentRepository: PostCommentRepository
) {

    fun getAll(from: Int = -1, to: Int = -1, userId: Int?): Result<List<PostDTO>> {
        val result = postRepository.getAll(from, to, userId)
        if (result.isFailure) return Result.failure(result.exceptionOrNull()!!)
        return Result.success(result.getOrThrow().map { it.toDTO() })
    }

    fun getById(id: Int, userId: Int?): Result<PostDTO> {
        val postResult = postRepository.getById(id, userId)
        if (postResult.isFailure) return Result.failure(Exception("Post not found"))
        return Result.success(postResult.getOrThrow().toDTO())
    }

    fun createPost(userId: Int, content: String, files: List<Result<StoredFile>>): Result<PostDTO> {
        val postIdResult = postRepository.createPost(userId, content, files)
        if (postIdResult.isFailure) return Result.failure(Exception("Post could not be created"))

        val postResult = postRepository.getById(postIdResult.getOrThrow(), userId)
        if (postResult.isFailure) return Result.failure(Exception("Post could not be created"))
        return Result.success(postResult.getOrThrow().toDTO())
    }

    fun likePost(postId: Int, userId: Int): Result<Boolean> {
        if (postRepository.isLikedBy(postId, userId).getOrThrow()) {
            val result = postRepository.removeLike(postId, userId)
            if (result.isFailure)
                return Result.failure(result.exceptionOrNull()!!)
            return Result.success(false)
        } else {
            val result = postRepository.addLike(postId, userId)
            if (result.isFailure)
                return Result.failure(result.exceptionOrNull()!!)
            return Result.success(true)
        }
    }

    fun createPostComment(userId: Int, postId: Int, content: String): Result<PostCommentDTO> {
        val postCommentIdResult = postCommentRepository.createPostComment(userId, postId, content)
        if (postCommentIdResult.isFailure) return Result.failure(Exception("Post comment could not be created"))

        val postResult = postCommentRepository.getById(postCommentIdResult.getOrThrow())
        if (postResult.isFailure) return Result.failure(Exception("Post could not be created"))
        return Result.success(postResult.getOrThrow().toDTO())
    }

    fun deletePost(postId: Int, userId: Int): Result<Unit> {
        val post = postRepository.getById(postId, userId)
        if (!post.isSuccess) {
            return Result.failure(Exception("Post not found"))
        }
        if (post.getOrThrow().user.id != userId) {
            return Result.failure(Exception("Post could not be deleted"))
        }

        val result = postRepository.deletePost(postId)
        if (result.isFailure)
            return Result.failure(result.exceptionOrNull()!!)
        return Result.success(Unit)
    }

    fun deletePostComment(commentId: Int, userId: Int): Result<Unit> {
        val comment = postCommentRepository.getById(commentId)
        if (!comment.isSuccess) {
            return Result.failure(Exception("Post not found"))
        }
        if (comment.getOrThrow().user.id != userId) {
            return Result.failure(Exception("Post could not be deleted"))
        }

        val result = postCommentRepository.deletePostComment(commentId)
        if (result.isFailure)
            return Result.failure(result.exceptionOrNull()!!)
        return Result.success(Unit)
    }
}