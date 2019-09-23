package com.grv.gauravtest.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.grv.gauravtest.app.RestaurantApplication
import com.grv.gauravtest.viewmodel.HomeViewModel

class HomeViewModelFactory(var application: RestaurantApplication) :ViewModelProvider.Factory {


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return HomeViewModel(application = application) as T
    }
}