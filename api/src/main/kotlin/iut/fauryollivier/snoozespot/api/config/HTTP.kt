package iut.fauryollivier.snoozespot.api.config

import com.asyncapi.kotlinasyncapi.context.service.AsyncApiExtension
import com.asyncapi.kotlinasyncapi.ktor.AsyncApiPlugin
import com.asyncapi.kotlinasyncapi.model.server.Server
import io.ktor.server.application.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.routing.*

fun Application.configureHTTP() {
    routing {
        openAPI(path = "openapi")
    }
//    install(HttpsRedirect) {
//            // The port to redirect to. By default 443, the default HTTPS port.
//            sslPort = 443
//            // 301 Moved Permanently, or 302 Found redirect.
//            permanentRedirect = true
//        }

    install(AsyncApiPlugin) {
        extension = AsyncApiExtension.builder {
            info {
                title("SnoozeSpot API")
                version("1.0.0")
            }
            servers {
                server("localhost") {
                    url = "http://localhost:8080"
                }
            }
        }
    }

    routing {
        swaggerUI(path = "swagger")
    }
}
