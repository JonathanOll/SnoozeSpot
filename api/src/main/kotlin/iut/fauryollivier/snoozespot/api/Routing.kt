package iut.fauryollivier.snoozespot.api

import io.ktor.http.*
import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.requestvalidation.RequestValidation
import io.ktor.server.plugins.requestvalidation.ValidationResult
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import iut.fauryollivier.snoozespot.models.Spot
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

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
        get("/name") {
            call.respond(DataTest("Ktor"))
        }

        get("/users") {
            call.respondText("""
                {
                    "user_id": 1,
                    "first_name": "Benji",
                    "last_name": "Cade",
                    "age": 84,
                    "email": "bcade0@google.de",
                    "country": "Thailand",
                    "postal_code": 34260,
                    "favorite_color": "red",
                    "nap_duration": 35,
                    "nap_location": "08786 Moulton Street"
                }
            """.trimIndent())
        }

        get("/") {
            call.respondText("Hello World!")
        }

        get("/spots/1") {
            val mockSpot = Spot(
                id = 1,
                creatorId = 42,
                name = "Chill Spot in the Woods",
                description = "A quiet and peaceful place surrounded by trees.",
                latitude = 45.764043,
                longitude = 4.835659,
                canBeDisplayed = true,
                createdAt = LocalDateTime.now().minusDays(5),
                deletedAt = null
            )

            call.respond(mockSpot)
        }

        // Static plugin. Try to access `/static/index.html`
        staticResources("/static", "static")
        get<Articles> { article ->
            // Get all articles ...
            call.respond("List of articles sorted starting from ${article.sort}")
        }
    }
}

@Serializable
@Resource("/articles")
class Articles(val sort: String? = "new")
