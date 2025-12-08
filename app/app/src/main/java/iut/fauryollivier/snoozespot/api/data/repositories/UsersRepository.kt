package iut.fauryollivier.snoozespot.api.data.repositories

import iut.fauryollivier.snoozespot.api.data.NetworkDataSource
import iut.fauryollivier.snoozespot.api.generated.model.CreatePostRequest
import iut.fauryollivier.snoozespot.api.generated.model.PostDTO
import iut.fauryollivier.snoozespot.api.generated.model.UserDTO
import okhttp3.ResponseBody
import retrofit2.Response
import java.util.UUID

object UsersRepository {

    suspend fun getUser(i: UUID): Response<UserDTO> {
        try {
            val post = NetworkDataSource.api.usersUuidGet(i.toString())
            return Response.success(post.body())
        } catch(e: Exception) {
            return Response.error(500, ResponseBody.EMPTY)
        }
    }

    suspend fun getUsers(): Response<List<UserDTO>> {
        try {
            val posts = NetworkDataSource.api.usersGet()
            return Response.success(posts.body())
        } catch(e: Exception) {
            return Response.error(500, ResponseBody.EMPTY)
        }
    }

}