package com.grv.gauravtest.model

import com.grv.gauravtest.app.RestaurantApplication
import com.grv.gauravtest.database.RestaurantDatabase
import com.grv.glammtest.database.RestaurantEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.withTimeout
import javax.inject.Inject


class RestaurantModel @Inject constructor() : IRestaurantModel,BaseModel() {
   val getDatabaseClient= RestaurantDatabase.getDatabase(RestaurantApplication.instance)


    override suspend fun addRestaurant(
       restaurantList: MutableList<RestaurantEntity>,
       callback: SuccessCallback
    ) {

        performOperationWithTimeOut({getDatabaseClient?.restDao()?.addRestaurant(restaurantList)},{ result ->
            callback.invoke(result)
        })

    }

  public  override suspend fun retriveRestaurantList(callback: (MutableList<RestaurantEntity>?) -> Unit) {
        val job= GlobalScope.async {
            withTimeout(TIMEOUT_DURATION_MILLIS){
                getDatabaseClient?.restDao()?.getAll()
            }
        }
        callback.invoke(job.await())

    }


}