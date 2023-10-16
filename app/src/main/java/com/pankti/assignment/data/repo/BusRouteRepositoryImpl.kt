package com.pankti.assignment.data.repo

import com.pankti.assignment.data.network.RetrofitAPI
import com.pankti.assignment.domain.BusDataUIModel
import com.pankti.assignment.domain.BusRouteDao
import com.pankti.assignment.domain.BusRouteRepository
import com.pankti.assignment.domain.RouteTiming
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class BusRouteRepositoryImpl @Inject constructor(private val api: RetrofitAPI ,
                                                 private val dao: BusRouteDao) : BusRouteRepository {


    override suspend fun fetchAndStoreBusRouteDetail(onFailure : (String) -> Unit) {
        flow {
           emit(api.getBusRoute())
        }.flowOn(Dispatchers.IO).catch { onFailure(it.message ?: "") }.collect{ _data ->

            val busRouteUiList: MutableList<BusDataUIModel> = mutableListOf()

            _data.routeInfo.forEach {
                val routeTimingList = arrayListOf<RouteTiming>()
                if (_data.routeTimings?.containsKey(it.id) == true) {

                    // to set default item
                    if (_data.routeTimings[it.id]?.isNotEmpty() == true) routeTimingList.add(RouteTiming())

                    routeTimingList.addAll(_data.routeTimings[it.id] ?: emptyList())
                }
                busRouteUiList.add(BusDataUIModel(it.id, it.name, it.source,
                    it.tripDuration, it.destination, routeTimingList))
            }
            dao.insertAll(busRouteUiList)
        }
    }

    override suspend fun getBusRouteDetailFromDB(): List<BusDataUIModel>{
        return dao.getBusRoutes()
    }

}