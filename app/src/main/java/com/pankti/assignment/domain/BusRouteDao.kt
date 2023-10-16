package com.pankti.assignment.domain

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BusRouteDao {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertAll(busDataList : List<BusDataUIModel>)

   @Query("select * from `bus-routes`")
   suspend fun getBusRoutes() : List<BusDataUIModel>
}