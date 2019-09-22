package com.grv.gauravtest.repo

import androidx.lifecycle.MutableLiveData
import com.grv.gauravtest.network.BaseClient
import com.grv.gauravtest.network.NetworkInterface
import com.grv.gauravtest.utils.NetworkConstant.Companion.BASE_URL
import com.grv.glammtest.database.RestaurantEntity
import com.grv.glammtest.network.response.ApiResponse
import com.grv.glammtest.network.response.geolocation.GeoLocationResponse
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseRepository : BaseClient() {
    private var networkInterface: NetworkInterface? = null


    abstract fun getRestorantByGEO(
        atitude: String,
        longitude: String, callBack: (ApiResponse<GeoLocationResponse>) -> Unit
    )

    abstract fun getLocalRestaurant(mutableRestaurantList: MutableLiveData<MutableList<RestaurantEntity>>)

    abstract fun insertRestaurant(restaurantList: MutableList<RestaurantEntity>)

    fun getAPIClient(): NetworkInterface {

        if (networkInterface == null) {
            networkInterface = createApiClient();
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