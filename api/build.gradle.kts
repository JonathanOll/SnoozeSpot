plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.plugin.serialization)
}

group = "iut.fauryollivier.snoozespot.api"
version = "0.0.1"

application {
    mainClass = "iut.fauryollivier.snoozespot.api.ApplicationKt"
}

dependencies {
    // Ktor Core & Server
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.server.host.common)
    implementation(libs.ktor.server.call.logging)
    implementation(libs.ktor.server.status.pages)
    implementation(libs.ktor.server.default.headers)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.resources)
    implementation(libs.ktor.server.request.validation)
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.auth.jwt)
    implementation(libs.ktor.server.http.redirect)
    implementation(libs.ktor.server.openapi)
    implementation(libs.ktor.server.swagger)
    implementation(libs.ktor.server.config.yaml)

    // Dependency Injection (Koin)
    implementation("io.insert-koin:koin-ktor:3.4.0")
    implementation("io.insert-koin:koin-logger-slf4j:3.4.0")

    // Database (Exposed + JDBC drivers)
    implementation("org.jetbrains.exposed:exposed-core:0.41.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.41.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.41.1")
    implementation("org.xerial:sqlite-jdbc:3.41.2.2")
    implementation("org.jetbrains.exposed:exposed-java-time:0.41.1")

    // AsyncAPI
    implementation(libs.kotlin.asyncapi.ktor)

    // Testing
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)

    //JWT
    implementation("io.ktor:ktor-server-core-jvm:2.0")
    implementation("io.ktor:ktor-server-netty-jvm:2.0")
    implementation("io.ktor:ktor-server-auth-jwt-jvm:2.0")
    implementation("com.auth0:java-jwt:4.0")
    implementation("org.mindrot:jbcrypt:0.4")

    //Dev certificate
    implementation("io.ktor:ktor-network-tls-certificates")

    // Hikari (pool pour la DB)
    implementation("com.zaxxer:HikariCP:5.1.0")

    // Google OAuth
    implementation("io.ktor:ktor-server-auth:2.3.7")
    implementation("io.ktor:ktor-server-auth-jwt:2.3.7")
    implementation("com.google.api-client:google-api-client:2.2.0")
    implementation("com.google.oauth-client:google-oauth-client:1.34.1")
    implementation("com.google.http-client:google-http-client-gson:1.43.3")
}

