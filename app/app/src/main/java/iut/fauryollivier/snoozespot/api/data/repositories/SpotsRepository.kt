package iut.fauryollivier.snoozespot.api.data.repositories

import android.util.Log
import com.google.android.gms.maps.model.LatLng
import iut.fauryollivier.snoozespot.api.data.NetworkDataSource
import iut.fauryollivier.snoozespot.api.generated.model.Post
import iut.fauryollivier.snoozespot.api.generated.model.Spot
import okhttp3.ResponseBody
import retrofit2.Response
import java.math.BigDecimal

object SpotsRepository {

    suspend fun getSpot(i: Int): Spot? {
        try {
//            val spot = NetworkDataSource.api.spotIdGet(i)
//            return spot.body()
            return null
        } catch(e: Exception) {
            return null
        }
    }

    suspend fun getSpotsZone(topLeft: LatLng, bottomRight: LatLng): Response<List<Spot>> {
        try {
            val spots = NetworkDataSource.api.spotsZoneGet(
                BigDecimal(topLeft.latitude),
                BigDecimal(topLeft.longitude),
                BigDecimal(bottomRight.latitude),
                BigDecimal(bottomRight.longitude)
            )

            Log.d("jonathan", spots.toString())
            return Response.success(spots.body())
        } catch(e: Exception) {
            return Response.error(500, ResponseBody.EMPTY)
        }
    }

}