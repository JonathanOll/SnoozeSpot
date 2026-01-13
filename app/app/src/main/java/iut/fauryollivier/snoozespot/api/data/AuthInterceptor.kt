package iut.fauryollivier.snoozespot.api.data

import android.content.Context
import iut.fauryollivier.snoozespot.datastore.TokenHolder
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val context: Context
) : okhttp3.Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val token = TokenHolder.token ?: return chain.proceed(originalRequest)

        return chain.proceed(
            originalRequest.newBuilder()
                .addHeader("Authorization", "Bearer $token")
                .build()
        )
    }

}