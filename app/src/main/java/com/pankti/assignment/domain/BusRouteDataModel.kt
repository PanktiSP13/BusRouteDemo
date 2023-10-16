package com.pankti.assignment.domain

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class BusRouteDataModel(
    val routeInfo: List<BusRouteInfo> = emptyList(),
    val routeTimings: Map<String, List<RouteTiming>>? = null
)

data class BusRouteInfo(
    val id: String="",
    val name: String="",
    val source: String="",
    val tripDuration: String="",
    val destination: String="",
    val icon: String=""
): Serializable {}


data class RouteTiming(
    val totalSeats: Int=0,
    @SerializedName("avaiable") val available : Int=0,
    val tripStartTime: String=""
) : Serializable

