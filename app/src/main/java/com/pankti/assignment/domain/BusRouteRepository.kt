package com.pankti.assignment.domain

interface BusRouteRepository {

    suspend fun fetchAndStoreBusRouteDetail(onFailure : (String) -> Unit)
    suspend fun getBusRouteDetailFromDB(): List<BusDataUIModel>
}