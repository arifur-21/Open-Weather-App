package com.example.openweatherapi

import com.example.openweatherapi.model.weathper.City
import javax.inject.Inject

class ApiServiceImp @Inject constructor (private val apiService: ApiService){

    suspend fun getCity(city: String): City = apiService.getCityData(city)
}