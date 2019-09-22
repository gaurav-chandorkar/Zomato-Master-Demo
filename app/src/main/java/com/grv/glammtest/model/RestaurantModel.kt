package com.grv.gauravtest.model

import com.grv.gauravtest.app.RestaurantApplication
import com.grv.gauravtest.database.RestaurantDatabase
import com.grv.glammtest.database.RestaurantEntity


class RestaurantModel : IRestaurantModel,BaseModel() {
   val db= RestaurantDatabase.getDatabase(RestaurantApplication.instance)
    //private val getDatabaseClient=RoomDatabaseClient.getInstance(RestaurantApplication.instance.applicationContext)
    override suspend fun addRestaurant(
       restaurantList: MutableList<RestaurantEntity>,
       callback: SuccessCallback
    ) {

        /*performOperationWithTimeOut({getDatabaseClient.restaurantDao().addRestaurant(restaurantList)},{result ->
            callback.invoke(result)
        })*/

    }

  public  override suspend fun retriveRestaurantList(callback: (MutableList<RestaurantEntity>?) -> Unit) {
        db?.wordDao()?.getAll()
        /*val job= GlobalScope.async {
            withTimeout(TIMEOUT_DURATION_MILLIS){
                getDatabaseClient.restaurantDao().getAllRestaurant()
            }

        }
        callback.invoke(job.await())*/

    }


}