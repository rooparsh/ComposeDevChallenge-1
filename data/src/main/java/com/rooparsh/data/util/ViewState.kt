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
