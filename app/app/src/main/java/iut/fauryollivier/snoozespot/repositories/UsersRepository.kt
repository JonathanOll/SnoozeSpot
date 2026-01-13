package iut.fauryollivier.snoozespot.repositories

import android.content.Context
import iut.fauryollivier.snoozespot.api.data.NetworkDataSource
import iut.fauryollivier.snoozespot.api.generated.model.UserDTO
import iut.fauryollivier.snoozespot.utils.buildFilePart
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

    suspend fun changeProfilePic(context: Context, uri: String): Response<Unit> {
        try {
            val result = NetworkDataSource.api.changeProfilePic(
                file = buildFilePart(context, uri)
            )
            return Response.success(Unit)
        } catch(e: Exception) {
            return Response.error(500, ResponseBody.EMPTY)
        }
    }

}