package iut.fauryollivier.snoozespot.api.routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.response.*
import io.ktor.server.routing.*
import iut.fauryollivier.snoozespot.api.services.UserService
import org.koin.ktor.ext.inject
import java.util.UUID

// route("/users")
fun Route.userRoutes() {
    val userService by inject<UserService>()

    get {
        val usersResult = userService.getAll()
        if (usersResult.isFailure) {
            call.respond(HttpStatusCode.InternalServerError)
            return@get
        }
        call.respond(usersResult.getOrThrow())
    }

    get("/{uuid}") {
        val uuid = try {
            UUID.fromString(call.parameters["uuid"].toString())
        } catch (e: IllegalArgumentException) {
            call.respond(HttpStatusCode.BadRequest, "Invalid user ID")
            return@get
        }

        val result = userService.getByUuid(uuid)
        if (result.isFailure) {
            call.respond(HttpStatusCode.NotFound, "User not found")
            return@get
        }
        call.respond(result.getOrThrow())
    }
}