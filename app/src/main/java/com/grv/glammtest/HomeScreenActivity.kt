package com.grv.glammtest

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.grv.gauravtest.app.RestaurantApplication
import com.grv.gauravtest.viewmodel.HomeViewModel
import com.grv.gauravtest.viewmodel.factory.HomeViewModelFactory
import com.grv.glammtest.database.RestaurantEntity
import com.grv.glammtest.view.HomeScreenView

class HomeScreenActivity : AppCompatActivity(),TouchActionDelegates {


    lateinit var viewmodelProvideFactory: HomeViewModelFactory
    val TAG = "HomeScreenActivity"
    lateinit var homeViewoMdel: HomeViewModel

    lateinit var contentView: HomeScreenView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e(TAG, "oncreate")

        contentView =
            LayoutInflater.from(this).inflate(R.layout.activity_home_screen, null) as HomeScreenView

        setContentView(contentView)
        contentView.initView(this@HomeScreenActivity)

        viewmodelProvideFactory = HomeViewModelFactory(application as RestaurantApplication)
        homeViewoMdel = ViewModelProviders.of(this@HomeScreenActivity, viewmodelProvideFactory)
            .get(HomeViewModel::class.java)
        observeLiveData()

    }


    private fun observeLiveData() {
        homeViewoMdel.getRestaurantList().observe(this@HomeScreenActivity,
            object : Observer<MutableList<RestaurantEntity>> {
                override fun onChanged(response: MutableList<RestaurantEntity>) {
                    contentView.closeProgress()

                    if (response == null) {
                        return
                    }

                    if (response.size > 0) {
                        Log.e(TAG, "got filtered list ${response.size}")
                        contentView.updateList(response)
                    }
                }

            })
    }

    override fun onItemClick(value: String) {
         Snackbar.make(contentView,value,Snackbar.LENGTH_SHORT).show()
    }
}


interface TouchActionDelegates {
    fun onItemClick(value: String)
}
