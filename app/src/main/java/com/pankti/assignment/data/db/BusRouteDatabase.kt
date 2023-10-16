package com.pankti.assignment.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pankti.assignment.MyApplication
import com.pankti.assignment.domain.BusDataUIModel
import com.pankti.assignment.domain.BusRouteDao

@Database(entities = [BusDataUIModel::class], version = 1, exportSchema = false)
@TypeConverters(DataListConverter::class)
abstract class BusRouteDatabase : RoomDatabase() {

    abstract fun busRouteDao() : BusRouteDao

    companion object {
        @Volatile
        private var instance: BusRouteDatabase? = null

        fun getDatabase(context: Context): BusRouteDatabase {
            val tempInstance = instance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val newInstance = Room.databaseBuilder(context.applicationContext, BusRouteDatabase::class.java,
                    "bus-route-database")
                    .allowMainThreadQueries()
                    .build()
                instance = newInstance
                return newInstance
            }
        }
    }
}
