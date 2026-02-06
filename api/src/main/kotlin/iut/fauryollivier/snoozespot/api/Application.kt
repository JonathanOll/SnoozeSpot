package iut.fauryollivier.snoozespot.api

import io.ktor.network.tls.certificates.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import iut.fauryollivier.snoozespot.api.auth.configureSecurity
import iut.fauryollivier.snoozespot.api.config.Config
import iut.fauryollivier.snoozespot.api.config.configureHTTP
import iut.fauryollivier.snoozespot.api.config.configureKoin
import iut.fauryollivier.snoozespot.api.config.configureMonitoring
import iut.fauryollivier.snoozespot.api.config.serialization.configureSerialization
import iut.fauryollivier.snoozespot.api.database.configureORM
import java.io.File
import java.security.KeyStore
import java.util.*

fun main(args: Array<String>) {
    embeddedServer(
        Netty,
        applicationEnvironment { },
        { envConfig() },
        module = Application::module
    ).start(wait = true)
}

fun Application.module() {
    configureORM()
    configureKoin()
    configureSerialization()
    configureMonitoring()
    configureSecurity()
    configureHTTP()
    configureRouting()
}

private fun ApplicationEngine.Configuration.envConfig() {
    val disableSSL = System.getenv("DISABLE_SSL") == "true"

    if (!disableSSL) {
        // LOCAL SSL
        val keyStoreFile = File("build/keystore.jks")
        println("Using certificate file: ${keyStoreFile.absolutePath}")

        val keyStore = buildKeyStore {
            certificate(Config.SSL_CERTIFICATE_ALIAS) {
                password = Config.SSL_CERTIFICATE_PASSWORD
                domains = Config.SSL_CERTIFICATE_DOMAINS
            }
        }

        keyStore.saveToFile(keyStoreFile, Config.SSL_CERTIFICATE_FILE_PASSWORD)

        val exportedCertificateForAndroid = File("build/exportedCertificateForAndroid.cer")
        exportCertificate(keyStore, Config.SSL_CERTIFICATE_ALIAS, exportedCertificateForAndroid)

        sslConnector(
            keyStore = keyStore,
            keyAlias = Config.SSL_CERTIFICATE_ALIAS,
            keyStorePassword = { Config.SSL_CERTIFICATE_FILE_PASSWORD.toCharArray() },
            privateKeyPassword = { Config.SSL_CERTIFICATE_PASSWORD.toCharArray() }
        ) {
            port = 443
            keyStorePath = keyStoreFile
        }
    } else {
        println("SSL DISABLED /!\\")
    }

    // POUR RENDER
    connector {
        port = System.getenv("PORT")?.toInt() ?: 8080
        host = "0.0.0.0"
    }
}


private fun exportCertificate(keyStore: KeyStore, alias: String, outputFile: File) {
    val cert = keyStore.getCertificate(alias) as? java.security.cert.X509Certificate
        ?: throw IllegalArgumentException("Alias $alias not found in KeyStore")

    val pem = buildString {
        append("-----BEGIN CERTIFICATE-----\n")
        append(Base64.getMimeEncoder(64, "\n".toByteArray()).encodeToString(cert.encoded))
        append("\n-----END CERTIFICATE-----\n")
    }

    outputFile.writeText(pem)
    println("Certificate exported to: ${outputFile.absolutePath}")
}