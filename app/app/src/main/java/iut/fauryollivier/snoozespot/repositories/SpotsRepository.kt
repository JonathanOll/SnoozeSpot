package iut.fauryollivier.snoozespot.repositories

import android.util.Log
import com.google.android.gms.maps.model.LatLng
import iut.fauryollivier.snoozespot.api.data.NetworkDataSource
import iut.fauryollivier.snoozespot.api.generated.model.CreatePostRequest
import iut.fauryollivier.snoozespot.api.generated.model.CreateSpotCommentRequest
import iut.fauryollivier.snoozespot.api.generated.model.PostCommentDTO
import iut.fauryollivier.snoozespot.api.generated.model.SpotCommentDTO
import iut.fauryollivier.snoozespot.api.generated.model.SpotDTO
import iut.fauryollivier.snoozespot.room.DatabaseBuilder
import okhttp3.ResponseBody
import org.openapitools.client.models.room.SpotDTORoomModel
import retrofit2.Response
import java.math.BigDecimal

object SpotsRepository {

    suspend fun getSpot(i: Int): Response<SpotDTO> {
        try {
            val post = NetworkDataSource.api.spotsIdGet(i)
            return Response.success(post.body())
        } catch(e: Exception) {
            return Response.error(500, ResponseBody.EMPTY)
        }
    }

    suspend fun getSpotsZone(topLeft: LatLng, bottomRight: LatLng): Response<List<SpotDTO>> {
        try {
            val spots = NetworkDataSource.api.spotsZoneGet(
                BigDecimal(topLeft.latitude),
                BigDecimal(topLeft.longitude),
                BigDecimal(bottomRight.latitude),
                BigDecimal(bottomRight.longitude)
            )
            return Response.success(spots.body())
        } catch(e: Exception) {
            return Response.error(500, ResponseBody.EMPTY)
        }
    }

    suspend fun createSpotComment(spotId: Int, content: String, rating: Int): Response<SpotCommentDTO> {
        try {
            val result = NetworkDataSource.api.spotsIdCommentPost(spotId, CreateSpotCommentRequest(content, rating))
            return Response.success(result.body())
        } catch(e: Exception) {
            return Response.error(500, ResponseBody.EMPTY)
        }
    }

    suspend fun saveOffline(spot: SpotDTO) {
        DatabaseBuilder.getInstance().SpotDao().insert(SpotDTORoomModel(
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
        ))
    }

    suspend fun removeOffline(id: Int) {
        return DatabaseBuilder.getInstance().SpotDao().deleteById(id)
    }

    suspend fun isSavedOffline(id: Int): Boolean {
        return DatabaseBuilder.getInstance().SpotDao().exist(id)
    }

    suspend fun getSpotsOffline(): Response<List<SpotDTO>> {
        try {
            return Response.success(DatabaseBuilder.getInstance().SpotDao().getAll().map { it.toApiModel() })
        } catch(e: Exception) {
            return Response.error(500, ResponseBody.EMPTY)
        }
    }

}