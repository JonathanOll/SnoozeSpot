package iut.fauryollivier.snoozespot.api.auth

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import iut.fauryollivier.snoozespot.api.repositories.UserRepository
import iut.fauryollivier.snoozespot.api.routes.authRoutes
import org.koin.dsl.module
import org.koin.ktor.ext.inject
import java.util.UUID

fun Application.configureSecurity() {
    val jwtService by inject<JWTService>()

    install(Authentication) {
        jwt("jwtAuth") {
            verifier(jwtService.verifier())
            validate { credential ->
                val sub = credential.payload.subject ?: return@validate null
                JWTPrincipal(credential.payload)
            }
            challenge { _, _ -> call.respond(HttpStatusCode.Unauthorized, "Invalid or missing token") }
        }
    }
}

val jwtModule = module {
    single {
        val secret = System.getenv("JWT_SECRET") ?: "dev-secret-to-change"
        val issuer = System.getenv("JWT_ISSUER") ?: "public-snooze-spot-api"
        val expiration = (System.getenv("JWT_EXPIRATION_SECONDS") ?: "3600").toLong()
        JWTService(secret, issuer, expiration)
    }
}

fun ApplicationCall.currentUserId(): Result<Int> {
    val uuidStr = principal<JWTPrincipal>()?.payload?.subject ?: return Result.failure(Exception("Unauthorized"))
    val uuid = try {
        UUID.fromString(uuidStr)
    } catch (e: IllegalArgumentException) {
        return Result.failure(Exception("Invalid token"))
    }
    val userRepository by inject<UserRepository>()
    val idResult = userRepository.getUserIdByUUID(uuid);
    if (idResult.isFailure) return Result.failure(Exception("Unauthorized"))
    return Result.success(idResult.getOrThrow())
}

