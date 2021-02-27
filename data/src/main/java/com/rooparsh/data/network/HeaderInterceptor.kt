package com.rooparsh.data.network

import com.rooparsh.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .header("x-api-key", BuildConfig.API_KEY)
                .build()
        )
    }

}