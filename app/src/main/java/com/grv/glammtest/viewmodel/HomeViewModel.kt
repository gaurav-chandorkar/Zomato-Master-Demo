package com.grv.gauravtest.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.grv.gauravtest.repo.HomeScreemRepository
import com.grv.gauravtest.utils.NetworkConstant
import com.grv.glammtest.database.RestaurantEntity
import com.grv.glammtest.network.response.ApiResponse
import com.grv.glammtest.network.response.geolocation.GeoLocationResponse
import kotlinx.coroutines.*

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val TAG = "HomeViewModel"
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var mutableGeoLiveData: MediatorLiveData<ApiResponse<GeoLocationResponse>> =
        MediatorLiveData()
    var _livedata: LiveData<ApiResponse<GeoLocationResponse>>

    var repository: HomeScreemRepository

    var mutableRestaurantList: MutableLiveData<List<RestaurantEntity>> = MutableLiveData()


   init {
       _livedata = mutableGeoLiveData
       repository = HomeScreemRepository()
       Log.e(TAG, "init called")

       loadRestorantByGEOLocation(
           NetworkConstant.lattitude,
           NetworkConstant.longitude
       ) // load restaurant from server
       repository.getLocalRestaurant(mutableRestaurantList)
   }




    private fun loadRestorantByGEOLocation(latitude: String, longitude: String) {


        mutableGeoLiveData.addSource(repository.getRestorantByGEO(
            latitude,
            longitude

        )

        ) { apiResponse ->

            getHotels(apiResponse)
        }

    }

    private fun getHotels(apiResponse: ApiResponse<GeoLocationResponse>) {

        uiScope. launch {
            var restaurantList = mutableListOf<RestaurantEntity>();
            for (nearby in apiResponse.posts.nearbyRestaurants) {

                var restaurant = nearby.restaurant.location.address?.let {
                    RestaurantEntity(id = nearby.restaurant.id, name = nearby.restaurant.name, thumb = nearby.restaurant.thumb, address = it)
                }
                restaurant?.let { restaurantList.add(restaurant) }


            }
            Log.e(TAG, "it works "+restaurantList.size)
            withContext(Dispatchers.Main){
                Log.e(TAG, "it works "+restaurantList.size)

                mutableRestaurantList.postValue(restaurantList)
                Log.e(TAG, "it works.......!!!!")

            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
