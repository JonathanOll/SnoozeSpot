package iut.fauryollivier.snoozespot.api

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import iut.fauryollivier.snoozespot.api.config.Config
import iut.fauryollivier.snoozespot.api.routes.authRoutes
import iut.fauryollivier.snoozespot.api.routes.postRoutes
import iut.fauryollivier.snoozespot.api.routes.spotRoutes
import iut.fauryollivier.snoozespot.api.routes.userRoutes

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
    }
    install(Resources)
//    install(RequestValidation) {
//        validate<String> { bodyText ->
//            if (!bodyText.startsWith("Hello"))
//                ValidationResult.Invalid("Body text should start with 'Hello'")
//            else ValidationResult.Valid
//        }
//    }

    routing {
        route("/auth") { authRoutes() }

        route("/posts") { postRoutes() }

        route("/spots") { spotRoutes() }

        route("/users") { userRoutes() }

        staticFileSystem(Config.STORED_FILE_REMOTE_PATH, Config.STORED_FILE_PATH)
    }
}
