package com.grv.glammtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.grv.gauravtest.app.RestaurantApplication
import com.grv.gauravtest.viewmodel.HomeViewModel
import com.grv.gauravtest.viewmodel.factory.HomeViewModelFactory
import com.grv.glammtest.network.response.ApiResponse
import com.grv.glammtest.network.response.geolocation.GeoLocationResponse

class HomeScreenActivity : AppCompatActivity() {


    lateinit var viewmodelProvideFactory: HomeViewModelFactory
    val TAG = "HomeScreenActivity"
    lateinit var homeViewoMdel: HomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_screen)
        viewmodelProvideFactory = HomeViewModelFactory(application as RestaurantApplication)
        Log.e(TAG, "oncreate")
        homeViewoMdel = ViewModelProviders.of(this@HomeScreenActivity, viewmodelProvideFactory)
            .get(HomeViewModel::class.java)
        observeLiveData()
    }
    private fun observeLiveData() {
        homeViewoMdel._livedata.observe(this@HomeScreenActivity,
            object : Observer<ApiResponse<GeoLocationResponse>> {
                override fun onChanged(response: ApiResponse<GeoLocationResponse>) {

                    Log.e(TAG,"got response")

                    if (response == null) {
                        return
                    }

                    if (response.error == null) {
                        Log.e(TAG,"got response2")
                    }
                }

            })
    }

}
