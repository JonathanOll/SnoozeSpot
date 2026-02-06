package iut.fauryollivier.snoozespot.api.config

import com.asyncapi.kotlinasyncapi.context.service.AsyncApiExtension
import com.asyncapi.kotlinasyncapi.ktor.AsyncApiPlugin
import io.ktor.server.application.*
import io.ktor.server.plugins.httpsredirect.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*

fun Application.configureHTTP() {
    val disableSSL = System.getenv("DISABLE_SSL") == "true"
    val disableAsync = System.getenv("DISABLE_ASYNCAPI") == "true"

    if (!disableSSL) {
        install(HttpsRedirect) {
            sslPort = 443
            permanentRedirect = true
        }
    }

    if(!disableAsync) {
        install(AsyncApiPlugin) {
            extension = AsyncApiExtension.builder {
                info {
                    title("SnoozeSpot API")
                    version("1.0.0")
                }
                servers {
                    server("localhost") {
                        url = "http://localhost:443"
                    }
                }
            }
        }
    }

    routing {
        swaggerUI(path = "swagger")
        openAPI(path = "openapi")
    }
}
