package com.pankti.assignment.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("bus-routes")
data class BusDataUIModel(
    @PrimaryKey val id: String="",
    val name: String="",
    val source: String="",
    val tripDuration: String="",
    val destination: String="",
    var isBusAvailable : Boolean = false,
    var busTrack : List<RouteTiming> = emptyList())