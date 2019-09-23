package com.grv.gauravtest.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.grv.glammtest.database.RestaurantEntity


@Dao
interface RestaurantDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRestaurant( list: MutableList<RestaurantEntity>)

   @Query("SELECT * FROM restaurantentity")
   fun getAll(): MutableList<RestaurantEntity>

    @Query("DELETE FROM restaurantentity")
    fun deleteAllRestaurant()

    /* @Query("SELECT * FROM user")
    fun getAllU(): List<User>*/
}
