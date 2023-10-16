package com.pankti.assignment.domain

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BusRouteViewModel @Inject constructor(private val repository: BusRouteRepository) : ViewModel() {

    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    private var _busDataModel: MutableLiveData<List<BusDataUIModel>> = MutableLiveData(emptyList())
    val busDataModel: LiveData<List<BusDataUIModel>> = _busDataModel

    var errorMessage = MutableLiveData<String>()

    init {
        fetchAndStoreBusRouteDetail()
    }

    private fun fetchAndStoreBusRouteDetail() {
        var count = 1
        viewModelScope.launch {
            do{
                repository.fetchAndStoreBusRouteDetail { errorMessage.postValue(it) }
                Log.e("@@@", "getBusRouteData: $count")
                count++
                getBusRouteDataFromDB()
                delay(60000)
            } while (true)

        }
    }

    private fun getBusRouteDataFromDB(){
        viewModelScope.launch{
           _busDataModel.postValue(repository.getBusRouteDetailFromDB())
        }
    }

    override fun onCleared() {
        super.onCleared()
        // Cancel the coroutine when the ViewModel is no longer used
        viewModelJob.cancel()
    }


}