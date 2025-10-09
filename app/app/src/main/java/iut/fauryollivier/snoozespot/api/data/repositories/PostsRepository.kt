package iut.fauryollivier.snoozespot.api.data.repositories

import iut.fauryollivier.snoozespot.api.data.NetworkDataSource
import iut.fauryollivier.snoozespot.generated.api.model.Post
import iut.fauryollivier.snoozespot.generated.api.model.Spot

object PostsRepository {

    suspend fun getPost(i: Int): Post? {
        try {
            val post = NetworkDataSource.api.postIdGet(i)
            return post.body()
        } catch(e: Exception) {
            return null
        }
    }

}