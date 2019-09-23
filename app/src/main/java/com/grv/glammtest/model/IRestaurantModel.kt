package com.grv.gauravtest.model

import com.grv.glammtest.database.RestaurantEntity


interface IRestaurantModel {

    suspend fun addRestaurant(
        restaurantList: MutableList<RestaurantEntity>,
        callback: SuccessCallback
    )

    suspend fun retriveRestaurantList(callback: (MutableList<RestaurantEntity>?) -> Unit)
    suspend fun deleteAll(callback:SuccessCallback)
}
typealias SuccessCallback = (Boolean) -> Unit
