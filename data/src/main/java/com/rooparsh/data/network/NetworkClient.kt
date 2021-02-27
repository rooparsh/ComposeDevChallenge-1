/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
            httpBuilder.addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
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
