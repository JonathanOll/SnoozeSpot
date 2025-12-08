package iut.fauryollivier.snoozespot.api.auth

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import iut.fauryollivier.snoozespot.api.config.Config
import iut.fauryollivier.snoozespot.api.repositories.UserRepository
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
        JWTService(Config.TOKEN_SECRET, Config.TOKEN_ISSUER, Config.TOKEN_EXPIRATION)
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

