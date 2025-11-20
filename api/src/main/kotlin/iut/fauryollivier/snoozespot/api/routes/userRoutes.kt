package iut.fauryollivier.snoozespot.api.routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.response.*
import io.ktor.server.routing.*
import iut.fauryollivier.snoozespot.api.services.UserService
import org.koin.ktor.ext.inject

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
}