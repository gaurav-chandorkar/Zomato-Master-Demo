package com.grv.gauravtest.repo

import com.grv.gauravtest.network.BaseClient
import com.grv.gauravtest.network.NetworkInterface
import com.grv.gauravtest.utils.NetworkConstant.Companion.BASE_URL
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

open class BaseRepository : BaseClient() {
    private var networkInterface: NetworkInterface? = null


    fun getAPIClient(): NetworkInterface {

        if (networkInterface==null){
            networkInterface=createApiClient();
        }
        return networkInterface as NetworkInterface
    }

    private fun createApiClient(): NetworkInterface {
        var logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        var client = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOkHttp())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return client.create(NetworkInterface::class.java)
    }
}