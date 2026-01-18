package iut.fauryollivier.snoozespot.api.utils

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.gson.GsonFactory

object GoogleTokenVerifier {

    private val transport = GoogleNetHttpTransport.newTrustedTransport()
    private val jsonFactory = GsonFactory.getDefaultInstance()

    private const val CLIENT_ID = "711264898485-176v0bef9johp8olmcj9p8fsev21o2ad.apps.googleusercontent.com"

    private val verifier = GoogleIdTokenVerifier.Builder(transport, jsonFactory)
        .setAudience(listOf(CLIENT_ID))
        .build()

    fun verify(idTokenString: String) =
        verifier.verify(idTokenString)
}
