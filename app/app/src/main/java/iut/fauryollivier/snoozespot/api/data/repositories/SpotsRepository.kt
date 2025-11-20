package iut.fauryollivier.snoozespot.api.data.repositories

import android.util.Log
import com.google.android.gms.maps.model.LatLng
import iut.fauryollivier.snoozespot.api.data.NetworkDataSource
import iut.fauryollivier.snoozespot.api.generated.model.SpotDTO
import okhttp3.ResponseBody
import retrofit2.Response
import java.math.BigDecimal

object SpotsRepository {

    suspend fun getSpot(i: Int): Response<SpotDTO> {
        try {
            val post = NetworkDataSource.api.spotsSpotsIdGet(i)
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

}