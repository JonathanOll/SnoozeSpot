package iut.fauryollivier.snoozespot.api.config

import com.asyncapi.kotlinasyncapi.context.service.AsyncApiExtension
import com.asyncapi.kotlinasyncapi.ktor.AsyncApiPlugin
import io.ktor.server.application.*
import io.ktor.server.plugins.httpsredirect.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*

fun Application.configureHTTP() {
    install(HttpsRedirect) {
        // The port to redirect to. By default 443, the default HTTPS port.
        sslPort = 443
        // 301 Moved Permanently, or 302 Found redirect.
        permanentRedirect = true
    }

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

    routing {
        swaggerUI(path = "swagger")
    }

    routing {
        openAPI(path = "openapi")
    }
}
