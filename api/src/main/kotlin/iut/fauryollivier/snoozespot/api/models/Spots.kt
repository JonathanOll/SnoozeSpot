package iut.fauryollivier.snoozespot.models

import java.time.LocalDateTime

data class Spot(
    val id: Int,
    val creatorId: Int,
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val canBeDisplayed: Boolean = true,
    val createdAt: LocalDateTime,
    val deletedAt: LocalDateTime? = null
)
