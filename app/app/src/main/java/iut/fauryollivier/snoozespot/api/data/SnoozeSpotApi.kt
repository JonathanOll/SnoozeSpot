package iut.fauryollivier.snoozespot.api.data

import iut.fauryollivier.snoozespot.api.generated.api.DefaultApi
import iut.fauryollivier.snoozespot.api.generated.model.SpotDTO
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface SnoozeSpotApi : DefaultApi {
    @Multipart
    @POST("spots")
    suspend fun createSpot(
        @Part("name") name: RequestBody,
        @Part("description") description: RequestBody,
        @Part("latitude") latitude: RequestBody,
        @Part("longitude") longitude: RequestBody,
        @Part files: List<MultipartBody.Part>
    ): Response<SpotDTO>
}
