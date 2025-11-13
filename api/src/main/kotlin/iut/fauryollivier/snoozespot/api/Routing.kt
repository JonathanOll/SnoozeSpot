package iut.fauryollivier.snoozespot.api

import authRoutes
import io.ktor.http.*
import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.resources.Resources
import io.ktor.server.response.*
import io.ktor.server.routing.*
import iut.fauryollivier.snoozespot.api.routes.userRoutes
import iut.fauryollivier.snoozespot.api.database.Tables
import iut.fauryollivier.snoozespot.api.models.Post
import iut.fauryollivier.snoozespot.api.routes.postRoutes
import iut.fauryollivier.snoozespot.api.routes.spotRoutes
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.time.LocalDateTime
import kotlin.Int

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause" , status = HttpStatusCode.InternalServerError)
        }
    }
    install(Resources)
    install(RequestValidation) {
        validate<String> { bodyText ->
            if (!bodyText.startsWith("Hello"))
                ValidationResult.Invalid("Body text should start with 'Hello'")
            else ValidationResult.Valid
        }
    }

    routing {
        userRoutes()
        postRoutes()
        spotRoutes()

        // Static plugin. Try to access `/static/index.html`
        // staticResources("/static", "static")
    }
}
