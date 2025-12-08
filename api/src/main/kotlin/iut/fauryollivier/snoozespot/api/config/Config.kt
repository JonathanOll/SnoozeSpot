package iut.fauryollivier.snoozespot.api.config

object Config {
    val TOKEN_SECRET = System.getenv("JWT_SECRET") ?: "dev-secret-to-change"
    val TOKEN_ISSUER = System.getenv("JWT_ISSUER") ?: "public-snooze-spot-api"
    val TOKEN_EXPIRATION = (System.getenv("JWT_EXPIRATION_SECONDS") ?: "3600").toLong()

    val SSL_CERTIFICATE_ALIAS = System.getenv("SSL_CERTIFICATE_ALIAS") ?: "certificateAlias"
    val SSL_CERTIFICATE_PASSWORD = System.getenv("SSL_CERTIFICATE_PASSWORD") ?: "my-best-password"
    val SSL_CERTIFICATE_FILE_PASSWORD = System.getenv("SSL_CERTIFICATE_FILE_PASSWORD") ?: "my-second-best-password"
    val SSL_CERTIFICATE_DOMAINS: List<String> = System.getenv("SSL_CERTIFICATE_DOMAINS")
        ?.split(",")
        ?.map { it.trim() }
        ?: listOf("127.0.0.1", "0.0.0.0", "localhost")

    val STORED_FILE_PATH = System.getenv("STORED_FILE_PATH") ?: ".\\src\\main\\resources\\static\\files"
}