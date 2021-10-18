package com.example.openweatherapi

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openweatherapi.model.weathper.City
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    val weatherResponse: MutableLiveData<City> = MutableLiveData()

    private val searchChannel = ConflatedBroadcastChannel<String>()


    fun setSearchQuery(search: String) {
        searchChannel.offer(search)
    }



    fun getCityData() {
        viewModelScope.launch {
            searchChannel.asFlow()
                    .flatMapLatest { search ->
                        repository.getCityData(search)
                    }.catch { e ->
                        Log.d("main", "${e.message}")
                    }.collect { response ->
                        weatherResponse.value = response
                    }
        }
    }
}






