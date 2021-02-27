package com.rooparsh.data.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommonResponse<out T>(
    @SerialName("status") val status: String = "",
    @SerialName("totalResults") val totalResults: Int = 0,
    @SerialName("articles") val data: T
)


@Serializable
data class ApiError(
    @SerialName("status") val status: String,
    @SerialName("message") val message: String,
    @SerialName("code") val code: String
)