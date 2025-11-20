package iut.fauryollivier.snoozespot.api.routes

import io.ktor.server.routing.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.http.*
import io.swagger.v3.oas.annotations.tags.Tag
import iut.fauryollivier.snoozespot.api.auth.model.UserAuthRequest
import iut.fauryollivier.snoozespot.api.dtos.UserDTO
import iut.fauryollivier.snoozespot.api.services.AuthService
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject
import kotlin.getOrThrow

@Serializable
class AuthResponseDTO(val accessToken: String, val expiresIn: Long, val user: UserDTO)


//  route("/auth")
fun Route.authRoutes() {
    val authService by inject<AuthService>()

    @Tag(name = "Auth Service")
    post("/signup") {
        val request = call.receive<UserAuthRequest>()
        val result: Result<AuthResponseDTO>
        try {
            result = authService.create(request)
            if(result.isFailure) call.respond(HttpStatusCode.Unauthorized)
            call.respond(HttpStatusCode.Created, result.getOrThrow())
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError)
            return@post
        }
    }

    @Tag(name = "Auth Service")
    post("/login") {
        val request = call.receive<UserAuthRequest>()
        try {
            val result = authService.login(request)
            if(result.isFailure) call.respond(HttpStatusCode.Unauthorized)
            call.respond(HttpStatusCode.Created, result.getOrThrow())
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError)
            return@post
        }
    }
}