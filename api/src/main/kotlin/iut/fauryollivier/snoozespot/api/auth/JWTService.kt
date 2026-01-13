package iut.fauryollivier.snoozespot.api.auth

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

class JWTService(secret: String, val issuer: String, val expirationSeconds: Long) {
    private val algorithm = Algorithm.HMAC256(secret)

    fun generateToken(userUUID: UUID): String {
        val now = Date()
        val expiresAt = Date(now.time + expirationSeconds * 1000)
        return JWT.create()
            .withIssuer(issuer)
            .withSubject(userUUID.toString())
            .withIssuedAt(now)
            .withExpiresAt(expiresAt)
            .sign(algorithm)
    }

    fun verifier(): JWTVerifier = JWT.require(algorithm).withIssuer(issuer).build()
}