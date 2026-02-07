package iut.fauryollivier.snoozespot.api.routes

import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.swagger.v3.oas.annotations.tags.Tag
import iut.fauryollivier.snoozespot.api.auth.model.UserAuthRequest
import iut.fauryollivier.snoozespot.api.dtos.UserDTO
import iut.fauryollivier.snoozespot.api.enums.StoredFileType
import iut.fauryollivier.snoozespot.api.enums.StoredFileUsage
import iut.fauryollivier.snoozespot.api.services.AuthService
import iut.fauryollivier.snoozespot.api.services.StoredFileService
import iut.fauryollivier.snoozespot.api.services.UserService
import iut.fauryollivier.snoozespot.api.utils.GoogleTokenVerifier
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject
import kotlin.getValue

@Serializable
class AuthResponseDTO(val accessToken: String, val expiresIn: Long, var user: UserDTO)

@Serializable
data class GoogleAuthRequest(val idToken: String)

@Serializable
data class AuthResponse(val token: String)


//  route("/auth")
fun Route.authRoutes() {
    val authService by inject<AuthService>()

    @Tag(name = "Auth Service")
    post("/signup") {
        val request = call.receive<UserAuthRequest>()
        val result: Result<AuthResponseDTO>
        try {
            result = authService.create(request)
            if (result.isFailure) call.respond(HttpStatusCode.Unauthorized)
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
            if (result.isFailure) call.respond(HttpStatusCode.Unauthorized)
            call.respond(HttpStatusCode.Created, result.getOrThrow())
        } catch (e: Exception) {
            call.respond(HttpStatusCode.InternalServerError)
            return@post
        }
    }

    @Tag(name = "Auth Service")
    post("/google") {
        val googleRequest = call.receive<GoogleAuthRequest>()

        try {
            val idToken = GoogleTokenVerifier.verify(googleRequest.idToken)
            if (idToken == null) {
                call.respond(HttpStatusCode.Unauthorized, "Token Google invalide")
                return@post
            }

            val payload = idToken.payload
            val googleId = payload.subject
            val email = payload.email
            val name = payload["name"] as? String
            val picture = payload["picture"] as? String

            val userService by inject<UserService>()

            val user = userService.getByEmail(
                email = email,
            )

            if(user.isFailure) {
                val result = authService.create(
                    UserAuthRequest(
                        username = name ?: email.split("@")[0],
                        password = "password",
                        email = email
                    )
                )

                if (result.isFailure) {
                    call.respond(HttpStatusCode.InternalServerError)
                    return@post
                }

                val storedFileService by inject<StoredFileService>()
                val file = storedFileService.saveFileFrom(
                    picture!!,
                    description = "profile picture",
                    type = StoredFileType.IMAGE,
                    usage = StoredFileUsage.PROFILE_PICTURE
                )

                if (file.isSuccess) {
                    val user = userService.changeProfilePicture(result.getOrThrow().user.uuid, file.getOrThrow().id!!)
                    val res = result.getOrThrow()
                    res.user = user.getOrThrow()

                    call.respond(HttpStatusCode.OK, res)
                    return@post
                }

                call.respond(HttpStatusCode.OK, result.getOrThrow())
                return@post
            }

            val jwt = authService.generate(user.getOrThrow())

            if (jwt.isFailure) {
                call.respond(HttpStatusCode.InternalServerError)
                return@post
            }


            call.respond(HttpStatusCode.OK, jwt.getOrThrow())

        } catch (e: Exception) {
            e.printStackTrace()
            call.respond(HttpStatusCode.InternalServerError, "Erreur lors de l'auth Google")
        }
    }

}