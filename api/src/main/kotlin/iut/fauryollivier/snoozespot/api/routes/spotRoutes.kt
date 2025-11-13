package iut.fauryollivier.snoozespot.api.routes

import io.ktor.http.HttpStatusCode
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
import iut.fauryollivier.snoozespot.api.services.SpotService
import kotlinx.serialization.Serializable
import org.koin.ktor.ext.inject

fun Route.spotRoutes() {
    val spotService by inject<SpotService>()

    route("/spots") {
        get {
            val spots = spotService.getAll() // Pagination simple
            call.respond(spots)
        }

        get("/zone") {
            val topLeftLatitude = call.request.queryParameters["topLeftLatitude"]?.toDoubleOrNull()
            val topLeftLongitude = call.request.queryParameters["topLeftLongitude"]?.toDoubleOrNull()
            val bottomRightLatitude = call.request.queryParameters["bottomRightLatitude"]?.toDoubleOrNull()
            val bottomRightLongitude = call.request.queryParameters["bottomRightLongitude"]?.toDoubleOrNull()

            if (topLeftLatitude != null && topLeftLongitude != null && bottomRightLatitude != null && bottomRightLongitude != null) {
                val spots = spotService.getAllInZone(
                    topLeftLatitude, topLeftLongitude, bottomRightLatitude, bottomRightLongitude
                )
                call.respond(spots)
            } else {
                call.respond(HttpStatusCode.BadRequest, "Invalid query parameters for the zone.")
            }
        }

        route("/spots/{id}") {
            get {
                val spotId = call.parameters["id"]?.toIntOrNull()
                if (spotId != null) {
                    val spot = spotService.getById(spotId)
                    if (spot != null) {
                        call.respond(spot)
                    } else {
                        call.respond(HttpStatusCode.NotFound, "Spot not found")
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Invalid spot ID")
                }
            }
        }
    }
}

