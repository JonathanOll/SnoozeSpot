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
            val user = NetworkDataSource.api.usersUuidGet(i.toString())
            if (user.isSuccessful)
                return Response.success(user.body())
            else
                return Response.error(500, ResponseBody.EMPTY)
        } catch(e: Exception) {
            return Response.error(500, ResponseBody.EMPTY)
        }
    }

    suspend fun changeProfilePic(context: Context, uri: String): Response<Unit> {
        try {
            val result = NetworkDataSource.api.changeProfilePic(
                file = buildFilePart(context, uri)
            )
            if (result.isSuccessful)
                return Response.success(Unit)
            else
                return Response.error(500, ResponseBody.EMPTY)
        } catch(e: Exception) {
            return Response.error(500, ResponseBody.EMPTY)
        }
    }

}