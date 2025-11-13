package iut.fauryollivier.snoozespot.api.auth.model

import kotlinx.serialization.Serializable

@Serializable
data class UserAuthRequest(
    val username: String,
    val password: String,
)