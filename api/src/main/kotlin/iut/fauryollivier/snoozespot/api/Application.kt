package iut.fauryollivier.snoozespot.api

import io.ktor.network.tls.certificates.buildKeyStore
import io.ktor.network.tls.certificates.saveToFile
import iut.fauryollivier.snoozespot.api.database.configureORM
import io.ktor.server.application.*
import io.ktor.server.engine.ApplicationEngine
import io.ktor.server.engine.applicationEnvironment
import io.ktor.server.engine.connector
import io.ktor.server.engine.embeddedServer
import io.ktor.server.engine.sslConnector
import io.ktor.server.netty.EngineMain
import io.ktor.server.netty.Netty
import iut.fauryollivier.snoozespot.api.config.configureHTTP
import iut.fauryollivier.snoozespot.api.config.configureKoin
import iut.fauryollivier.snoozespot.api.config.configureMonitoring
import iut.fauryollivier.snoozespot.api.auth.configureSecurity
import iut.fauryollivier.snoozespot.api.config.serialization.configureSerialization
import java.io.File
import java.security.KeyStore
import java.util.Base64

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
    val certificateAlias = System.getenv("SSL_CERTIFICATE_ALIAS") ?: "certificateAlias"
    val certificatePassword = System.getenv("SSL_CERTIFICATE_PASSWORD") ?: "my-best-password"
    val certificateFilePassword = System.getenv("SSL_CERTIFICATE_FILE_PASSWORD") ?: "my-second-best-password"

    val certificateDomains: List<String> = System.getenv("SSL_CERTIFICATE_DOMAINS")
        ?.split(",")
        ?.map { it.trim() }
        ?: listOf("127.0.0.1", "0.0.0.0", "localhost")

    val keyStoreFile = File("build/keystore.jks")
    println("Using certificate file: ${keyStoreFile.absolutePath}")
    val keyStore = buildKeyStore {
        certificate(certificateAlias) {
            password = certificatePassword
            domains = certificateDomains
        }
    }
    keyStore.saveToFile(keyStoreFile, certificateFilePassword)

    val exportedCertificateForAndroid = File("build/exportedCertificateForAndroid.cer")
    exportCertificate(keyStore, certificateAlias, exportedCertificateForAndroid)

    connector {
        port = 80
    }

    sslConnector(
        keyStore = keyStore,
        keyAlias = certificateAlias,
        keyStorePassword = { certificateFilePassword.toCharArray() },
        privateKeyPassword = { certificatePassword.toCharArray() }) {
        port = 443
        keyStorePath = keyStoreFile
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