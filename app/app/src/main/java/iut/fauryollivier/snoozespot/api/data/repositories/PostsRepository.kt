package iut.fauryollivier.snoozespot.api.data.repositories

import android.util.Log
import iut.fauryollivier.snoozespot.api.data.NetworkDataSource
import iut.fauryollivier.snoozespot.generated.api.model.Post
import iut.fauryollivier.snoozespot.generated.api.model.Spot

object PostsRepository {

    suspend fun getPost(i: Int): Post? {
        try {
            val post = NetworkDataSource.api.postsIdGet(i)
            return post.body()
        } catch(e: Exception) {
            return null
        }
    }

    suspend fun getPosts(): List<Post>? {
        try {
            val posts = NetworkDataSource.api.postsGet()
            return posts.body()
        } catch(e: Exception) {
            return null
        }
    }

}