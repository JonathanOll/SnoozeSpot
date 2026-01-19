package iut.fauryollivier.snoozespot.repositories

import android.content.Context
import android.util.Log
import iut.fauryollivier.snoozespot.R
import iut.fauryollivier.snoozespot.api.data.NetworkDataSource
import iut.fauryollivier.snoozespot.api.generated.model.CreatePostCommentRequest
import iut.fauryollivier.snoozespot.api.generated.model.PostCommentDTO
import iut.fauryollivier.snoozespot.api.generated.model.PostDTO
import iut.fauryollivier.snoozespot.room.DatabaseBuilder
import iut.fauryollivier.snoozespot.utils.Toaster
import iut.fauryollivier.snoozespot.utils.buildFileParts
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import org.openapitools.client.models.room.PostDTORoomModel
import retrofit2.Response

object PostsRepository {

    suspend fun getPost(i: Int): Response<PostDTO> {
        try {
            val post = NetworkDataSource.api.postsIdGet(i)
            if (post.isSuccessful)
                return Response.success(post.body())
        } catch (_: Exception) {}

        return Response.error(500, ResponseBody.EMPTY)
    }

    suspend fun getPosts(page: Int = 0): Response<List<PostDTO>> {
        val db = DatabaseBuilder.getInstance()
        try {
            val posts = NetworkDataSource.api.postsGet(page)

            if (posts.isSuccessful) {
                val body = posts.body() ?: emptyList()
                db.savePosts(body)
                return Response.success(body)
            }

        } catch (_: Exception) {}

        if (page == 0 && db.PostDao().count() != 0) {
            val local = db.PostDao().getAll().map { it.toApiModel() }
            Toaster.instance.toast(R.string.offline_data)
            return Response.success(local)
        }

        return Response.error(500, ResponseBody.EMPTY)
    }

    suspend fun createPost(
        context: Context,
        content: String,
        files: List<String>
    ): Response<PostDTO> {
        try {
            val post = NetworkDataSource.api.createPost(
                content.toRequestBody(),
                buildFileParts(context, files)
            )
            if (post.isSuccessful)
                return Response.success(post.body())
        } catch (_: Exception) {}

        return Response.error(500, ResponseBody.EMPTY)
    }

    suspend fun likePost(postId: Int): Response<Boolean> {
        try {
            val result = NetworkDataSource.api.postsIdLikePost(postId)
            if (result.isSuccessful)
                return Response.success(result.body())
        } catch (_: Exception) {}

        return Response.error(500, ResponseBody.EMPTY)
    }

    suspend fun createPostComment(postId: Int, content: String): Response<PostCommentDTO> {
        try {
            val result = NetworkDataSource.api.postsIdCommentPost(postId, CreatePostCommentRequest(content))
            if (result.isSuccessful)
                return Response.success(result.body())
        } catch (_: Exception) {}

        return Response.error(500, ResponseBody.EMPTY)
    }

    suspend fun deletePost(postId: Int): Response<Unit> {
        try {
            val result = NetworkDataSource.api.postsIdDelete(postId)
            if (result.isSuccessful)
                return Response.success(Unit)
        } catch(_: Exception) {}

        return Response.error(500, ResponseBody.EMPTY)
    }

    suspend fun deletePostComment(commentId: Int): Response<Unit> {
        try {
            val result = NetworkDataSource.api.postsCommentCommentIdDelete(commentId)
            if (result.isSuccessful)
                return Response.success(Unit)
        } catch(_: Exception) {}

        return Response.error(500, ResponseBody.EMPTY)
    }

}