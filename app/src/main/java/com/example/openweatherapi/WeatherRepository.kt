package com.example.openweatherapi

import com.example.openweatherapi.model.weathper.City
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class WeatherRepository @Inject constructor(private val apiService: ApiServiceImp) {


    fun getCityData(city:String):Flow<City> = flow {
        val response = apiService.getCity(city)
        emit(response)
    }.flowOn(Dispatchers.IO)
        .conflate()

}