package com.grv.gauravtest.model

import com.grv.glammtest.database.RestaurantEntity


interface IRestaurantModel {

    suspend fun addRestaurant(restaurantList:MutableList<RestaurantEntity>, callback:SuccessCallback)

    suspend fun retriveRestaurantList(callback: (MutableList<RestaurantEntity>?) -> Unit )
}
typealias SuccessCallback=(Boolean) -> Unit
