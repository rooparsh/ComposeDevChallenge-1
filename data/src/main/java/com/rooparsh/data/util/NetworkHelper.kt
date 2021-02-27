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
package com.rooparsh.data.util

import com.rooparsh.data.network.model.ApiError
import com.rooparsh.data.network.model.DataResult
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.ResponseBody
import retrofit2.Response

suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>): DataResult<T> {
    return withContext(Dispatchers.IO + exceptionHandler) {
        apiCall.invoke().resolveResponse()
    }
}

private fun <T> Response<T>.resolveResponse(): DataResult<T> {
    return if (this.isSuccessful) {
        this.body()?.let {
            DataResult.Success(it)
        } ?: kotlin.run { DataResult.Failure(this.errorBody().toApiError()) }
    } else {
        handleFailure()
    }
}

private fun <T> Response<T>.handleFailure(): DataResult.Failure {
    return DataResult.Failure(this.errorBody().toApiError())
}

private fun ResponseBody?.toApiError(): ApiException {
    return ApiException(
        Json {
            ignoreUnknownKeys = true
            isLenient = true
        }.decodeFromString(
            ApiError.serializer(),
            this?.charStream()?.readText().orEmpty()
        )
    )
}

private val exceptionHandler =
    CoroutineExceptionHandler { _, exception ->
        DataResult.Failure(exception)
    }

class ApiException(apiError: ApiError) : Exception(apiError.message)
