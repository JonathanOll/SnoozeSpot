package iut.fauryollivier.snoozespot.api.data.repositories

import iut.fauryollivier.snoozespot.api.generated.model.Spot

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

}