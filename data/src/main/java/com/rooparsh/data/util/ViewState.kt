package com.rooparsh.data.util

sealed class ViewState<out ResultType> {
    data class Success<out ResultType>(val data: ResultType) : ViewState<ResultType>()
    object Loading : ViewState<Nothing>()
    object Initial : ViewState<Nothing>()
    data class Error<out ResultType>(val throwable: Throwable?) : ViewState<ResultType>()

    companion object {
        fun <ResultType> success(data: ResultType): ViewState<ResultType> = Success(data)
        fun loading(): ViewState<Nothing> = Loading
        fun initial(): ViewState<Nothing> = Initial
        fun <ResultType> error(throwable: Throwable? = null): ViewState<ResultType> =
            Error(throwable)
    }
}