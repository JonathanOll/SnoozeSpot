package iut.fauryollivier.snoozespot.api.data.repositories

import iut.fauryollivier.snoozespot.api.data.NetworkDataSource
import iut.fauryollivier.snoozespot.api.generated.model.CreatePostRequest
import iut.fauryollivier.snoozespot.api.generated.model.PostDTO
import okhttp3.ResponseBody
import retrofit2.Response

object PostsRepository {

    suspend fun getPost(i: Int): Response<PostDTO> {
        try {
            val post = NetworkDataSource.api.postsIdGet(i)
            return Response.success(post.body())
        } catch(e: Exception) {
            return Response.error(500, ResponseBody.EMPTY)
        }
    }

    suspend fun getPosts(page: Int = 0): Response<List<PostDTO>> {
        try {
            val posts = NetworkDataSource.api.postsGet(page)
            return Response.success(posts.body())
        } catch(e: Exception) {
            return Response.error(500, ResponseBody.EMPTY)
        }
    }

    suspend fun createPost(content: String): Response<PostDTO> {
        try {
            val post = NetworkDataSource.api.postsPost(CreatePostRequest(content))
            return Response.success(post.body())
        } catch(e: Exception) {
            return Response.error(500, ResponseBody.EMPTY)
        }
    }

}