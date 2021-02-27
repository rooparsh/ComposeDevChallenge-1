package com.rooparsh.data.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.rooparsh.data.BuildConfig
import com.rooparsh.data.network.api.DogApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object NetworkClient {

    private val contentType = "application/json".toMediaType()

    private fun createOkHttpClient(): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            httpBuilder.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }

        httpBuilder.addInterceptor(HeaderInterceptor())
        return httpBuilder.build()
    }

    private fun createServiceProvider(client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(client)
        .addConverterFactory(createJsonConfig().asConverterFactory(contentType))
        .build()

    private fun createJsonConfig() = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    fun provideDogApi(): DogApi {
        val retrofit = createServiceProvider(createOkHttpClient())
        return retrofit.create(DogApi::class.java)
    }

}