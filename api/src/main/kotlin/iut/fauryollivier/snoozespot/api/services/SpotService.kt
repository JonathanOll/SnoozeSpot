package iut.fauryollivier.snoozespot.api.services

import iut.fauryollivier.snoozespot.api.models.Spot
import iut.fauryollivier.snoozespot.api.repositories.SpotRepository
import org.koin.ktor.ext.inject
import kotlin.IllegalArgumentException

class SpotService(private val spotRepository: SpotRepository) {

    fun getAll(): List<Spot> {
        return spotRepository.getAll()
    }

    fun getById(id: Int): Spot? {
        return spotRepository.getById(id)
    }

    fun getAllInZone(
        topLeftLatitude: Double,
        topLeftLongitude: Double,
        bottomRightLatitude: Double,
        bottomRightLongitude: Double
    ): List<Spot> {
        return spotRepository.getAllInZone(topLeftLatitude, topLeftLongitude, bottomRightLatitude, bottomRightLongitude)
    }
}
