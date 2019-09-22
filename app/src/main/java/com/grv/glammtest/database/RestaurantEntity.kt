package com.grv.glammtest.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity()
data class RestaurantEntity constructor(
    @PrimaryKey
    @ColumnInfo(name = "rest_id") var id: String,
    @ColumnInfo(name= "name") var name: String, @ColumnInfo(name= "thumb") var thumb: String
    , @ColumnInfo(name= "address") var address: String
) {


}

class RestaurantEntity1 {

}