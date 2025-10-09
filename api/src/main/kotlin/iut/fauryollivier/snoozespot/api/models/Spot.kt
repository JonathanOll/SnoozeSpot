package iut.fauryollivier.snoozespot.api.models

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class Spot(
    val id: Int,
    val creator: User,
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    val canBeDisplayed: Int, // 1 or 0
    val likeCount: Int,
    @Contextual val createdAt: LocalDateTime,
    val pictures: List<File> = emptyList(),
    val attributes: List<SpotAttribute> = emptyList(),
    val comments: List<SpotComment> = emptyList(),
)
