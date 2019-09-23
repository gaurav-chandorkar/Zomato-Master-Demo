package com.grv.glammtest.toothpick

import com.grv.gauravtest.model.IRestaurantModel
import com.grv.gauravtest.model.RestaurantModel
import com.grv.gauravtest.repo.BaseRepository
import com.grv.gauravtest.repo.HomeScreemRepository
import toothpick.Toothpick
import toothpick.config.Module

object RepoScope{

    var scope= Toothpick.openScopes(this).apply {
        installModules(RepoModule)
    }

    object RepoModule: Module(){

        init {
            bind(IRestaurantModel::class.java).toInstance(RestaurantModel())
           // bind(BaseRepository::class.java).toInstance(HomeScreemRepository())

        }
    }
}

object ViewModelScope{
    var scope=Toothpick.openScope(this).apply {
        installModules(ViewModelModule)
    }

    object ViewModelModule:Module(){

        init {
             bind(HomeScreemRepository::class.java).toInstance(HomeScreemRepository())
            bind(IRestaurantModel::class.java).toInstance(RestaurantModel())

        }
    }
}
