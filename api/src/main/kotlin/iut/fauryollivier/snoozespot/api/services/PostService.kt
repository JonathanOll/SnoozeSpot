package iut.fauryollivier.snoozespot.api.services

import iut.fauryollivier.snoozespot.api.database.Tables
import iut.fauryollivier.snoozespot.api.models.Post
import iut.fauryollivier.snoozespot.api.repositories.PostRepository
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.transactions.transaction

class PostService(private val postRepository: PostRepository) {

    fun getAll(from: Int = -1, to: Int = -1): List<Post> {
        return postRepository.getAll(from, to)
    }

    fun getById(id: Int): Post? {
        return postRepository.getById(id)
    }

    fun createPost(userId: Int, content: String): Post? {
        val postId = postRepository.createPost(userId, content)
        return getById(postId) ?: throw IllegalArgumentException("Post could not be created")
    }
}