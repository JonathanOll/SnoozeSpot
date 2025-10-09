package iut.fauryollivier.snoozespot.api.services

import iut.fauryollivier.snoozespot.api.models.Post
import iut.fauryollivier.snoozespot.api.repositories.PostRepository

class PostService(private val postRepository: PostRepository) {

    fun getAll(): List<Post> {
        return postRepository.getAll()
    }

    fun getById(id: Int): Post? {
        return postRepository.getById(id)
    }
}