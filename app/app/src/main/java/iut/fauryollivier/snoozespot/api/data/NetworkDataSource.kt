package iut.fauryollivier.snoozespot.api.data

import android.content.Context
import iut.fauryollivier.snoozespot.api.generated.api.DefaultApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkDataSource {
    private const val BASE_URL = "https://localhost:8080/"

    private lateinit var apiInstance: DefaultApi

    fun init(context: Context) {
        val intercepter = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(intercepter)
            this.addInterceptor(AuthInterceptor(context))
        }.build()

        apiInstance = Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DefaultApi::class.java)
    }

    val api: DefaultApi
        get() = apiInstance
}