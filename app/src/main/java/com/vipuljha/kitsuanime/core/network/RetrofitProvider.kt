package com.vipuljha.kitsuanime.core.network

import com.vipuljha.kitsuanime.BuildConfig
import com.vipuljha.kitsuanime.core.utils.Constants.BASE_URL
import com.vipuljha.kitsuanime.features.anime.data.datasources.network.KitsuApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitProvider {
    private fun createOkHttpClient(enableLogging: Boolean = BuildConfig.DEBUG): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)

        if (enableLogging) {
            client.addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            })
        }

        return client.build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(if (BASE_URL.endsWith("/")) BASE_URL else "$BASE_URL/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpClient())
            .build()
    }

    val animeApi: KitsuApi by lazy {
        retrofit.create(KitsuApi::class.java)
    }
}