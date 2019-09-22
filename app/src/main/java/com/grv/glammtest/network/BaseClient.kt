package com.grv.gauravtest.network

import com.grv.gauravtest.utils.NetworkConstant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit


open class BaseClient {

    fun getOkHttp(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient()
            .newBuilder()
            .connectTimeout(NetworkConstant.HTTP_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(NetworkConstant.HTTP_READ_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(NetworkConstant.HTTP_WRITE_TIMEOUT, TimeUnit.SECONDS)

            .addInterceptor(interceptor)
            .build()
    }

}