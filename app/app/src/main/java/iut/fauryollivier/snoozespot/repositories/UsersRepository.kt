package iut.fauryollivier.snoozespot.repositories

import android.content.Context
import iut.fauryollivier.snoozespot.api.data.NetworkDataSource
import iut.fauryollivier.snoozespot.api.generated.model.AuthResponseDTO
import iut.fauryollivier.snoozespot.api.generated.model.UserAuthRequest
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
        } catch (_: Exception) {}

        return Response.error(500, ResponseBody.EMPTY)
    }

    suspend fun changeProfilePic(context: Context, uri: String): Response<UserDTO> {
        try {
            val result = NetworkDataSource.api.changeProfilePic(
                file = buildFilePart(context, uri)
            )
            if (result.isSuccessful)
                return Response.success(result.body())
        } catch (_: Exception) {}

        return Response.error(500, ResponseBody.EMPTY)
    }

    suspend fun login(username: String, password: String): Response<AuthResponseDTO> {
         try {
            val result = NetworkDataSource.api.authLoginPost(UserAuthRequest(username, password))
            if (result.isSuccessful)
                return Response.success(result.body())
        } catch (_: Exception) {}

        return Response.error(500, ResponseBody.EMPTY)
    }

    suspend fun register(
        email: String,
        username: String,
        password: String
    ): Response<AuthResponseDTO> {
        try {
            val result =
                NetworkDataSource.api.authSignupPost(UserAuthRequest(username, password, email))
            if (result.isSuccessful)
                return Response.success(result.body())
        } catch (_: Exception) {}

        return Response.error(500, ResponseBody.EMPTY)
    }

    suspend fun getMe(): Response<UserDTO> {
        try {
            val result = NetworkDataSource.api.usersMeGet()
            if (result.isSuccessful)
                return Response.success(result.body())
        } catch(_: Exception) {}

        return Response.error(500, ResponseBody.EMPTY)
    }

}