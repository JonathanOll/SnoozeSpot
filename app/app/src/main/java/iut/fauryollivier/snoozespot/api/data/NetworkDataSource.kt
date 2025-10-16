package iut.fauryollivier.snoozespot.api.data

import iut.fauryollivier.snoozespot.api.generated.api.DefaultApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkDataSource {
    private const val BASE_URL = "http://localhost:8080"

    private val intercepter = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }
    private val client = OkHttpClient.Builder().apply {
        this.addInterceptor(intercepter)
            // time out setting
            .connectTimeout(3,TimeUnit.SECONDS)
            .readTimeout(20,TimeUnit.SECONDS)
            .writeTimeout(25,TimeUnit.SECONDS)

    }.build()

    val api: DefaultApi by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DefaultApi::class.java)
    }
}