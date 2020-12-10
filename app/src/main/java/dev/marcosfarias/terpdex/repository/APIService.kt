package dev.marcosfarias.terpdex.repository

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object APIService {
    private val interceptor = run {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    public val retrofit = Retrofit.Builder()
        .baseUrl("https://ajdev7.github.io/TerpDex-Media/")
        .addConverterFactory(GsonConverterFactory.create())
//        .client(okHttpClient)
        .build()
}
