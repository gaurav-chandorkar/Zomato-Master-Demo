package com.grv.gauravtest.model

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.withTimeout
import java.lang.Exception
const val TIMEOUT_DURATION_MILLIS = 3000L

open class BaseModel {
    // single function to perform operation in background,
     suspend fun performOperationWithTimeOut(function: () -> Unit, callback: SuccessCallback) {
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
    }

}