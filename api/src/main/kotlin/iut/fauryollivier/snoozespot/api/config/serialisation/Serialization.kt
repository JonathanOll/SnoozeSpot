package iut.fauryollivier.snoozespot.api.config.serialisation

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import java.time.LocalDateTime
import java.util.UUID

fun Application.configureSerialization() {
    val module = SerializersModule {
        contextual(LocalDateTime::class, LocalDateTimeSerializer)
        contextual(UUID::class, UUIDSerializer)
    }

    install(ContentNegotiation) {
        json(
            Json {
                serializersModule = module
                prettyPrint = true
                encodeDefaults = true
            }
        )
    }
}
