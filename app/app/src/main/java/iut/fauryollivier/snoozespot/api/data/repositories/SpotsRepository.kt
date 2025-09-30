package iut.fauryollivier.snoozespot.api.data.repositories

import iut.fauryollivier.snoozespot.api.data.NetworkDataSource
import iut.fauryollivier.snoozespot.generated.api.model.Spot

class SpotsRepository {

    suspend fun getSpot(): Spot? {
        try {
            val spot = NetworkDataSource.api.spots1Get()
            return spot.body()
        } catch(e: Exception) {
            return null
        }
    }

}