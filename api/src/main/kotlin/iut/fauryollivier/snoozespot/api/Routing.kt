package iut.fauryollivier.snoozespot.api

import io.ktor.http.*
import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.resources.Resources
import io.ktor.server.response.*
import io.ktor.server.routing.*
import iut.fauryollivier.snoozespot.api.routes.userRoutes
import kotlinx.serialization.Serializable


@Serializable
@Resource("spot/{id}")
data class SpotById(val id: Int)

@Serializable
@Resource("post/{id}")
data class PostById(val id: Int)

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

        // Static plugin. Try to access `/static/index.html`
        // staticResources("/static", "static")
    }
}

@Serializable
@Resource("/articles")
class Articles(val sort: String? = "new")
