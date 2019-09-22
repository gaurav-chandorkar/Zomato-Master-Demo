package com.grv.gauravtest.model

import java.lang.Exception
const val TIMEOUT_DURATION_MILLIS = 3000L

open class BaseModel {

     /*suspend fun performOperationWithTimeOut(function: () -> Unit, callback: SuccessCallback) {
          var job= GlobalScope.async {

        try {
            withTimeout(TIMEOUT_DURATION_MILLIS){
                function.invoke()
            }
        }catch (e:Exception){
            e.printStackTrace()
            callback.invoke(false)
        }
    }
         job.await()
         callback.invoke(true)
    }*/

}