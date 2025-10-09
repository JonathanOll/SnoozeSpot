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
import iut.fauryollivier.snoozespot.api.database.Tables
import iut.fauryollivier.snoozespot.api.models.Post
import iut.fauryollivier.snoozespot.models.Spot
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.select
import java.time.LocalDateTime


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
        get("/") {
            call.respondText("Hello World!")
        }

        get<SpotById> { params ->
            val mockSpot = Spot(
                id = params.id,
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

        get<PostById> { params ->

            val data = Tables.Posts
                        .leftJoin(Tables.Users)
                        .select(Tables.Posts.content, Tables.Posts.id, Tables.Users.id, Tables.Posts.createdAt, Tables.Users.username)
                        .where { Tables.Posts.id eq params.id }
            println(data)
            call.respond(data)
            //TODO: finir ça


            // Mock data for the post
            val mockPost = Post(
                id = params.id,
                userId = 1,
                content = "This is a sample post content",
                likeCount = 123,
                createdAt = LocalDateTime.now().minusDays(3),
                deletedAt = null
            )

            // Respond with the mock post
            call.respond(mockPost)
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
