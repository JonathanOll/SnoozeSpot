package iut.fauryollivier.snoozespot.api.data.repositories

import android.util.Log
import iut.fauryollivier.snoozespot.api.data.NetworkDataSource
import iut.fauryollivier.snoozespot.generated.api.model.CreatePostRequest
import iut.fauryollivier.snoozespot.generated.api.model.Post
import iut.fauryollivier.snoozespot.generated.api.model.Spot
import okhttp3.ResponseBody
import retrofit2.Response

object PostsRepository {

    suspend fun getPost(i: Int): Response<Post> {
        try {
            val post = NetworkDataSource.api.postsIdGet(i)
            return Response.success(post.body())
        } catch(e: Exception) {
            return Response.error(500, ResponseBody.EMPTY)
        }
    }

    suspend fun getPosts(page: Int = 0): Response<List<Post>> {
        try {
            val posts = NetworkDataSource.api.postsGet(page)
            return Response.success(posts.body())
        } catch(e: Exception) {
            return Response.error(500, ResponseBody.EMPTY)
        }
    }

    suspend fun createPost(content: String): Response<Post> {
        try {
            val post = NetworkDataSource.api.postsPost(CreatePostRequest(content))
            return Response.success(post.body())
        } catch(e: Exception) {
            return Response.error(500, ResponseBody.EMPTY)
        }
    }

}