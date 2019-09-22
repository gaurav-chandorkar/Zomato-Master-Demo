package com.grv.gauravtest.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.grv.gauravtest.model.RestaurantModel
import com.grv.glammtest.database.RestaurantEntity
import com.grv.glammtest.network.response.ApiResponse
import com.grv.glammtest.network.response.geolocation.GeoLocationResponse
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeScreemRepository : BaseRepository() {

    private var restaurantodel: RestaurantModel? = null
    val TAG="HomeScreemRepository"

    init {
        restaurantodel= RestaurantModel()
    }
    fun getRestorantByGEO(
        latitude: String,
        longitude: String


    ): MutableLiveData<ApiResponse<GeoLocationResponse>> {
        var mutableGeoLiveData = MutableLiveData<ApiResponse<GeoLocationResponse>>()
        var call = getAPIClient().getRestorantByGEO(latitude, longitude)

        call.enqueue(object : Callback<GeoLocationResponse> {
            override fun onFailure(call: Call<GeoLocationResponse>, t: Throwable) {
                mutableGeoLiveData.postValue(ApiResponse(t))
            }

            override fun onResponse(
                call: Call<GeoLocationResponse>,
                response: Response<GeoLocationResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        mutableGeoLiveData.postValue(ApiResponse(it))
                    }
                }
            }

        })
        return mutableGeoLiveData
    }

    fun getLocalRestaurant(mutableRestaurantList: MutableLiveData<List<RestaurantEntity>>) {
        GlobalScope.async {
            restaurantodel?.retriveRestaurantList { nullableList ->
            mutableRestaurantList.postValue(nullableList)
            Log.e(TAG, "restaurant list")
            }

        }
    }

}