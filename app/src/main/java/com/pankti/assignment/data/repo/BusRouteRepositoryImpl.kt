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
//            val str = "{\n" + "    \"routeInfo\": [\n" + "        {\n" + "            \"id\": \"r002\",\n" + "            \"name\": \"k-12\",\n" + "            \"source\": \"Yashwantpur\",\n" + "            \"tripDuration\":\"2hrs\",\n" + "            \"destination\": \"Marathahalli\",\n" + "            \"icon\": \"http://\"\n" + "        }, {\n" + "            \"id\": \"r003\",\n" + "            \"name\": \"k-11\",\n" + "            \"tripDuration\":\"45 min\",\n" + "            \"source\": \"Koramangala\",\n" + "            \"destination\": \"Bomanhalli\",\n" + "            \"icon\": \"http://\"\n" + "        }, {\n" + "            \"id\": \"r004\",\n" + "            \"name\": \"k-14\",\n" + "            \"source\": \"E City\",\n" + "            \"tripDuration\":\"1hrs\",\n" + "            \"destination\": \"Silk Board\",\n" + "            \"icon\": \"http://\"\n" + "        }, {\n" + "            \"id\": \"r001\",\n" + "            \"name\": \"R-1\",\n" + "            \"source\": \"Marathahalli\",\n" + "            \"tripDuration\":\"2hrs\",\n" + "            \"destination\": \"E City\",\n" + "            \"icon\": \"http://\"\n" + "        }, {\n" + "            \"id\": \"r005\",\n" + "            \"name\": \"G-12\",\n" + "            \"tripDuration\":\"2hrs\",\n" + "            \"source\": \"Koramangala\",\n" + "            \"destination\": \"E City\",\n" + "            \"icon\": \"http://\"\n" + "        }\n" + "    ],\n" + "    \"routeTimings\": {\n" + "        \"r002\": [{\n" + "                \"totalSeats\": 13,\n" + "                \"avaiable\": 5,\n" + "                \"tripStartTime\": \"18:40\"\n" + "            }, {\n" + "                \"totalSeats\": 13,\n" + "                \"avaiable\": 0,\n" + "                \"tripStartTime\": \"18:48\"\n" + "            },\n" + "            {\n" + "                \"totalSeats\": 13,\n" + "                \"avaiable\": 1,\n" + "                \"tripStartTime\": \"19:05\"\n" + "            }\n" + "        ],\n" + "        \"r005\": [{\n" + "                \"totalSeats\": 13,\n" + "                \"avaiable\": 5,\n" + "                \"tripStartTime\": \"19:10\"\n" + "            }, {\n" + "                \"totalSeats\": 13,\n" + "                \"avaiable\": 0,\n" + "                \"tripStartTime\": \"19:00\"\n" + "            },\n" + "            {\n" + "                \"totalSeats\": 13,\n" + "                \"avaiable\": 1,\n" + "                \"tripStartTime\": \"19:05\"\n" + "            }\n" + "        ],\n" + "        \"r001\": [],\n" + "        \"r004\": [{\n" + "                \"totalSeats\": 13,\n" + "                \"avaiable\": 5,\n" + "                \"tripStartTime\": \"14:55\"\n" + "            }, {\n" + "                \"totalSeats\": 13,\n" + "                \"avaiable\": 0,\n" + "                \"tripStartTime\": \"15:00\"\n" + "            },\n" + "            {\n" + "                \"totalSeats\": 13,\n" + "                \"avaiable\": 1,\n" + "                \"tripStartTime\": \"15:05\"\n" + "            }\n" + "        ],\n" + "        \"r003\": [{\n" + "                \"totalSeats\": 12,\n" + "                \"avaiable\": 10,\n" + "                \"tripStartTime\": \"15:55\"\n" + "            }, {\n" + "                \"totalSeats\": 12,\n" + "                \"avaiable\": 9,\n" + "                \"tripStartTime\": \"20:00\"\n" + "            },\n" + "            {\n" + "                \"totalSeats\": 12,\n" + "                \"avaiable\": 1,\n" + "                \"tripStartTime\": \"19:05\"\n" + "            }\n" + "        ]\n" + "    }\n" + "}\n"
//            val data: BusRouteDataModel = Gson().fromJson(str, BusRouteDataModel::class.java)
//            emit(data)
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