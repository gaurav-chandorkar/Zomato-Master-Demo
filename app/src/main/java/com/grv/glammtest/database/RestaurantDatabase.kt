package com.grv.gauravtest.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.grv.glammtest.database.RestaurantEntity

@Database(entities = [RestaurantEntity::class], version = 2)
abstract class RestaurantDatabase : RoomDatabase() {
    abstract fun wordDao(): RestaurantDAO

    //abstract fun userDao(): UserDao

    companion object {

        @Volatile
        private var INSTANCE: RestaurantDatabase? = null

        fun getDatabase(context: Context): RestaurantDatabase? {
            Log.e("RestaurantDatabase grv", "Opened")

            if (INSTANCE == null) {
                synchronized(RestaurantDatabase::class.java) {
                    if (INSTANCE == null) {
                        // Create database here
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            RestaurantDatabase::class.java, "word_database"
                        )
                            .addCallback(sRoomDatabaseCallback).build()
                    }
                }
            }
            return INSTANCE
        }

        private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                Log.e("RestaurantDatabase grv", "Opened")
            }
        }
    }


}