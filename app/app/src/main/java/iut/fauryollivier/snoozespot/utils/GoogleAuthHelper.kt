package iut.fauryollivier.snoozespot.utils

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

object GoogleAuthHelper {
    fun getClient(context: Context): GoogleSignInClient {
        val options = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken("711264898485-176v0bef9johp8olmcj9p8fsev21o2ad.apps.googleusercontent.com")
            .build()

        return GoogleSignIn.getClient(context, options)
    }
}
