package iut.fauryollivier.snoozespot.api.models

import kotlinx.serialization.Serializable

@Serializable
data class SpotAttribute(
    val id: Int,
    val name: String,
    val icon: File?
)
