package com.grv.gauravtest.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.grv.gauravtest.model.IRestaurantModel
import com.grv.glammtest.database.RestaurantEntity
import com.grv.glammtest.network.response.ApiResponse
import com.grv.glammtest.network.response.geolocation.GeoLocationResponse
import com.grv.glammtest.toothpick.RepoScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import toothpick.Toothpick
import javax.inject.Inject

class HomeScreemRepository @Inject constructor() : BaseRepository() {

    @Inject
    lateinit var restaurantodel: IRestaurantModel
    val TAG = "HomeScreemRepository"

    init {
        Toothpick.inject(this, RepoScope.scope)
    }

    override fun getRestorantByGEO(
        latitude: String,
        longitude: String, callBack: (ApiResponse<GeoLocationResponse>) -> Unit


    ) {
        var call = getAPIClient().getRestorantByGEO(latitude, longitude)

        call.enqueue(object : Callback<GeoLocationResponse> {
            override fun onFailure(call: Call<GeoLocationResponse>, t: Throwable) {
                callBack.invoke(ApiResponse(t))

            }

            override fun onResponse(
                call: Call<GeoLocationResponse>,
                response: Response<GeoLocationResponse>

            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callBack.invoke(ApiResponse(it))
                    }
                }
            }

        })
    }

    override fun getLocalRestaurant(mutableRestaurantList: MutableLiveData<MutableList<RestaurantEntity>>) {
        GlobalScope.async {
            restaurantodel.retriveRestaurantList { nullableList ->
                mutableRestaurantList.postValue(nullableList)
                Log.e(TAG, "restaurant list ${nullableList?.size}")
            }
        }
    }

    override fun insertRestaurant(restaurantList: MutableList<RestaurantEntity>) {
        GlobalScope.async {

            restaurantodel.deleteAll {
                Log.e(TAG, "All Restaurant Deleted")
            }
            restaurantodel.addRestaurant(restaurantList) {
                if (it) {
                    Log.e(TAG, "Restaurant inserted")
                } else
                    Log.e(TAG, "Restaurant insert Failed")

            }

        }

    }


}