package iut.fauryollivier.snoozespot.api.routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import iut.fauryollivier.snoozespot.api.auth.currentUserId
import iut.fauryollivier.snoozespot.api.services.SpotService
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject

@Serializable
data class CreateSpotRequest(
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
)

// route("/spots")
fun Route.spotRoutes() {
    val spotService by inject<SpotService>()

    get {
        val spotsResult = spotService.getAll() // Pagination simple
        if (spotsResult.isFailure) {
            call.respond(HttpStatusCode.InternalServerError)
            return@get
        }
        call.respond(spotsResult.getOrThrow())
    }

    get("/zone") {
        val topLeftLatitude = call.request.queryParameters["topLeftLatitude"]?.toDoubleOrNull()
        val topLeftLongitude = call.request.queryParameters["topLeftLongitude"]?.toDoubleOrNull()
        val bottomRightLatitude = call.request.queryParameters["bottomRightLatitude"]?.toDoubleOrNull()
        val bottomRightLongitude = call.request.queryParameters["bottomRightLongitude"]?.toDoubleOrNull()

        if( topLeftLatitude == null || topLeftLongitude == null || bottomRightLatitude == null || bottomRightLongitude == null) {
            call.respond(HttpStatusCode.BadRequest, "Missing query parameters for the zone.")
            return@get
        }

        val spotsResult = spotService.getAllInZone( topLeftLatitude, topLeftLongitude, bottomRightLatitude, bottomRightLongitude )
        if (spotsResult.isFailure) {
            call.respond(HttpStatusCode.InternalServerError)
            return@get
        }
        call.respond(spotsResult.getOrThrow())
    }

    route("/spots/{id}") {
        get {
            val spotId = call.parameters["id"]?.toIntOrNull()
            if (spotId == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid spot ID")
                return@get
            }
            val spotResult = spotService.getById(spotId)
            if (spotResult.isFailure) {
                call.respond(HttpStatusCode.NotFound, "Spot not found")
                return@get
            }
            call.respond(spotResult.getOrThrow())
        }
    }

    authenticate("jwtAuth") {
        post {
            val userId = call.currentUserId().getOrThrow()
            val request = call.receive<CreateSpotRequest>()
            try {
                val post = spotService.createSpot(userId, request)
                call.respond(HttpStatusCode.Created, post!!)
            } catch (e: Exception) {
                print(e.toString())
                call.respond(HttpStatusCode.BadRequest, "Post creation failed")
            }
        }
    }
}


