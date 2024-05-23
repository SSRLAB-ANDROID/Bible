package by.ssrlab.bible.client

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private const val BASE_URL = "https://api.scripture.api.bible/v1/"
    private const val HEADER_KEY = "13b3fc242f9824945b73752630ab0945"
    private const val TIME_OUT = 30L

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("api-key", HEADER_KEY)
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    fun create(): Retrofit {
        return Retrofit.Builder()
            .client(createOkHttpClient())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}