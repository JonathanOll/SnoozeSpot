package iut.fauryollivier.snoozespot.api.data

import android.content.Context
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object NetworkDataSource {
//    const val BASE_URL = "https://localhost:8080/"
    const val BASE_URL = "https://snoozespot.onrender.com/"

    private lateinit var apiInstance: SnoozeSpotApi

    fun init(context: Context) {
        val intercepter = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
        val client = OkHttpClient.Builder().apply {
            this.addInterceptor(intercepter)
            this.addInterceptor(AuthInterceptor(context))

            this.connectTimeout(60, TimeUnit.SECONDS)
            this.readTimeout(60, TimeUnit.SECONDS)
            this.writeTimeout(60, TimeUnit.SECONDS)
        }.build()

        apiInstance = Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SnoozeSpotApi::class.java)
    }

    val api: SnoozeSpotApi
        get() = apiInstance
}