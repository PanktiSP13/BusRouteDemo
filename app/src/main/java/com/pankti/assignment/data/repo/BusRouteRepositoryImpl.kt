package com.pankti.assignment.data.repo

import android.util.Log
import com.pankti.assignment.data.network.RetrofitAPI
import com.pankti.assignment.domain.BusDataUIModel
import com.pankti.assignment.domain.BusRouteDao
import com.pankti.assignment.domain.BusRouteRepository
import com.pankti.assignment.domain.RouteTiming
import com.pankti.assignment.ui.Utils.isBusAvailable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BusRouteRepositoryImpl @Inject constructor(private val api: RetrofitAPI,
                                                 private val dao: BusRouteDao) : BusRouteRepository {


    override suspend fun fetchAndStoreBusRouteDetail(onFailure: (String) -> Unit) {
        flow {
           emit(api.getBusRoute())

        }.flowOn(Dispatchers.IO).catch { onFailure(it.message ?: "") }.collect { _data ->

            val busRouteUiList: MutableList<BusDataUIModel> = mutableListOf()

            _data.routeInfo.forEach {

                val dataUIModel = BusDataUIModel(id = it.id, name = it.name, source = it.source, tripDuration = it.tripDuration, destination = it.destination)

                val routeTimingList = arrayListOf<RouteTiming>()
                if (_data.routeTimings?.containsKey(it.id) == true) {

                    // to set default item
                    if (_data.routeTimings[it.id]?.isNotEmpty() == true) {
                        routeTimingList.add(RouteTiming())

                        var isBusAvailableNow = false
                        for (i in _data.routeTimings[it.id] ?: emptyList()) {
                           if (i.tripStartTime.isBusAvailable()){
                               isBusAvailableNow = true
                               break
                           }
                        }
                        routeTimingList.addAll(_data.routeTimings[it.id] ?: emptyList())

                        dataUIModel.isBusAvailable = isBusAvailableNow
                        dataUIModel.busTrack = routeTimingList
                        Log.e("@@@", "isBusAvailable: "+dataUIModel.isBusAvailable )

                    }
                }
                busRouteUiList.add(dataUIModel)
            }
            dao.insertAll(busRouteUiList)
        }
    }

    override suspend fun getBusRouteDetailFromDB(): List<BusDataUIModel> {
        return dao.getBusRoutes()
    }

}