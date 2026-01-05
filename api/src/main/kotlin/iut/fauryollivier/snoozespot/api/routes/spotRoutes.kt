package iut.fauryollivier.snoozespot.api.routes

import io.ktor.http.HttpStatusCode
import io.ktor.http.content.PartData
import io.ktor.http.content.forEachPart
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.request.receiveMultipart
import io.ktor.server.response.*
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import iut.fauryollivier.snoozespot.api.auth.currentUserId
import iut.fauryollivier.snoozespot.api.auth.handleMultipart
import iut.fauryollivier.snoozespot.api.dtos.StoredFileDTO
import iut.fauryollivier.snoozespot.api.entities.StoredFile
import iut.fauryollivier.snoozespot.api.enums.StoredFileType
import iut.fauryollivier.snoozespot.api.enums.StoredFileUsage
import iut.fauryollivier.snoozespot.api.services.SpotService
import iut.fauryollivier.snoozespot.api.services.StoredFileService
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject
import kotlin.getValue

@Serializable
data class CreateSpotRequest(
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
)

@Serializable
data class CreateSpotCommentRequest(
    val content: String,
    val rating: Int,
)

// route("/spots")
fun Route.spotRoutes() {
    val spotService by inject<SpotService>()
    val storedFileService by inject<StoredFileService>()

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

    route("/{id}") {
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

            val (request, files) = call.handleMultipart<CreateSpotRequest>(storedFileService)

            val spot = spotService.createSpot(userId, request, files)

            if (spot.isFailure) {
                call.respond(HttpStatusCode.BadRequest, "Post creation failed")
                return@post
            }

            call.respond(HttpStatusCode.Created, spot.getOrThrow())
        }

        post("{id}/comment") {
            val userId = call.currentUserId().getOrThrow()
            val spotId = call.parameters["id"]?.toIntOrNull()
            if (spotId == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid spot ID")
                return@post
            }
            val spotResult = spotService.getById(spotId)
            if (spotResult.isFailure) {
                call.respond(HttpStatusCode.NotFound, "Spot not found")
                return@post
            }

            val request = call.receive<CreateSpotCommentRequest>()

            val result = spotService.createSpotComment(userId!!, spotId, request.content, request.rating)
            if (result.isFailure) {
                call.respond(HttpStatusCode.BadRequest, "Could not comment spot $spotId")
                return@post
            }
            call.respond(result.getOrThrow())
        }
    }
}


