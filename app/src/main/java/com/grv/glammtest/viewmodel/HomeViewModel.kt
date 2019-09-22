package com.grv.gauravtest.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.grv.gauravtest.repo.BaseRepository
import com.grv.gauravtest.repo.HomeScreemRepository
import com.grv.gauravtest.utils.NetworkConstant
import com.grv.glammtest.database.RestaurantEntity
import com.grv.glammtest.network.response.ApiResponse
import com.grv.glammtest.network.response.geolocation.GeoLocationResponse
import com.grv.glammtest.toothpick.RepoScope
import com.grv.glammtest.toothpick.ViewModelScope
import kotlinx.coroutines.*
import toothpick.Toothpick
import javax.inject.Inject

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    val TAG = "HomeViewModel"
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    @Inject
   lateinit var repository: HomeScreemRepository

    private var mutableRestaurantList: MutableLiveData<MutableList<RestaurantEntity>> = MutableLiveData()


   init {
       Toothpick.inject(this, ViewModelScope.scope)

       Log.e(TAG, "init called")

        repository.getRestorantByGEO( NetworkConstant.lattitude,
           NetworkConstant.longitude){ apiResponse ->
           Log.e(TAG,"callback works")
           getHotels(apiResponse)               // load from Server
       }


       repository.getLocalRestaurant(mutableRestaurantList) // load from local database
   }


    fun getRestaurantList():LiveData<MutableList<RestaurantEntity>>{
        return mutableRestaurantList
    }

    private fun getHotels(apiResponse: ApiResponse<GeoLocationResponse>) {
        if (apiResponse.posts==null)
            return
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
            repository.insertRestaurant(restaurantList)
        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
