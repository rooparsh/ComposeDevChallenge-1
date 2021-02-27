package com.rooparsh.data.network.model

sealed class DataResult<out T> {
    data class Success<out T>(val body: T) : DataResult<T>()
    data class Failure(val throwable: Throwable) : DataResult<Nothing>()
}
