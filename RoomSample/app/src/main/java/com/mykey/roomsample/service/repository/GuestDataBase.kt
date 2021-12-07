package com.example.guests.service.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mykey.roomsample.service.model.GuestModel
import com.mykey.roomsample.service.repository.GuestDAO

@Database(entities = [GuestModel::class], version = 1)
abstract class GuestDataBase() : RoomDatabase() {

    abstract fun guestDao(): GuestDAO

    companion object {

        private lateinit var INSTANCE: GuestDataBase
        fun getDataBase(context: Context): GuestDataBase {
            if (!::INSTANCE.isInitialized) {
                synchronized(GuestDataBase::class) {
                    INSTANCE = Room.databaseBuilder(context, GuestDataBase::class.java, "Guest")
                        .allowMainThreadQueries()
                        .build()
                }
            }

            return INSTANCE

        }
    }
}