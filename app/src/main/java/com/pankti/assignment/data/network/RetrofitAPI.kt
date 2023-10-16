package com.pankti.assignment.data.network

import com.pankti.assignment.domain.BusRouteDataModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitAPI {

    @GET("v1/59546f23-ac45-443c-b02a-e6f1002217cd")
    suspend fun getBusRoute() : BusRouteDataModel
}