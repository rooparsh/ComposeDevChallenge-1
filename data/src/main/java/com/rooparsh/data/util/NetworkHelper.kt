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