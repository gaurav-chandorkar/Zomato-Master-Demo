package com.grv.gauravtest.app

import android.app.Application

class RestaurantApplication: Application() {

    companion object {
        lateinit var instance:RestaurantApplication
    }

    init {
        instance=this
    }
}