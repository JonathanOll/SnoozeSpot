package iut.fauryollivier.snoozespot.repositories

import android.content.Context
import com.google.android.gms.maps.model.LatLng
import iut.fauryollivier.snoozespot.api.data.NetworkDataSource
import iut.fauryollivier.snoozespot.api.generated.model.CreateSpotCommentRequest
import iut.fauryollivier.snoozespot.api.generated.model.SpotCommentDTO
import iut.fauryollivier.snoozespot.api.generated.model.SpotDTO
import iut.fauryollivier.snoozespot.room.DatabaseBuilder
import iut.fauryollivier.snoozespot.utils.buildFileParts
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import org.openapitools.client.models.room.SpotDTORoomModel
import retrofit2.Response
import java.math.BigDecimal

object SpotsRepository {

    suspend fun getSpot(i: Int): Response<SpotDTO> {
        return try {
            val spot = NetworkDataSource.api.spotsIdGet(i)
            if (spot.isSuccessful)
                Response.success(spot.body())
            else
                Response.error(500, ResponseBody.EMPTY)
        } catch (e: Exception) {
            Response.error(500, ResponseBody.EMPTY)
        }
    }

    suspend fun createSpot(
        context: Context,
        name: String,
        description: String,
        latitude: Double,
        longitude: Double,
        files: List<String>
    ): Response<SpotDTO> {
        return try {
            val result = NetworkDataSource.api.createSpot(
                name.toRequestBody(), description.toRequestBody(),
                latitude.toString().toRequestBody(), longitude.toString().toRequestBody(),
                files = buildFileParts(context, files)
            )
            if (result.isSuccessful)
                Response.success(result.body())
            else
                Response.error(500, ResponseBody.EMPTY)
        } catch (e: Exception) {
            Response.error(500, ResponseBody.EMPTY)
        }
    }

    suspend fun getSpotsZone(topLeft: LatLng, bottomRight: LatLng): Response<List<SpotDTO>> {
        return try {
            val spots = NetworkDataSource.api.spotsZoneGet(
                BigDecimal(topLeft.latitude),
                BigDecimal(topLeft.longitude),
                BigDecimal(bottomRight.latitude),
                BigDecimal(bottomRight.longitude)
            )
            if (spots.isSuccessful)
                Response.success(spots.body())
            else
                Response.error(500, ResponseBody.EMPTY)
        } catch (e: Exception) {
            Response.error(500, ResponseBody.EMPTY)
        }
    }

    suspend fun createSpotComment(
        spotId: Int,
        content: String,
        rating: Int
    ): Response<SpotCommentDTO> {
        return try {
            val result = NetworkDataSource.api.spotsIdCommentPost(
                spotId,
                CreateSpotCommentRequest(content, rating)
            )
            if (result.isSuccessful)
                Response.success(result.body())
            else
                Response.error(500, ResponseBody.EMPTY)
        } catch (e: Exception) {
            Response.error(500, ResponseBody.EMPTY)
        }
    }

    suspend fun saveOffline(spot: SpotDTO) {
        DatabaseBuilder.getInstance().SpotDao().insert(
            SpotDTORoomModel(
                roomTableId = spot.id,
                id = spot.id,
                name = spot.name,
                description = spot.description,
                latitude = spot.latitude,
                longitude = spot.longitude,
                likeCount = spot.likeCount,
                creator = spot.creator,
                rating = spot.rating,
                createdAt = spot.createdAt,
            )
        )
    }

    suspend fun removeOffline(id: Int) {
        return DatabaseBuilder.getInstance().SpotDao().deleteById(id)
    }

    suspend fun isSavedOffline(id: Int): Boolean {
        return DatabaseBuilder.getInstance().SpotDao().exist(id)
    }

    suspend fun getSpotsOffline(): Response<List<SpotDTO>> {
        return try {
            Response.success(
                DatabaseBuilder.getInstance().SpotDao().getAll().map { it.toApiModel() })
        } catch (e: Exception) {
            Response.error(500, ResponseBody.EMPTY)
        }
    }

}