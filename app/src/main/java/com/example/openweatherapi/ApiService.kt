package com.example.openweatherapi

import com.example.openweatherapi.model.weathper.City
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {



    @GET("weather/")
    suspend fun getCityData(
        @Query("q") q:String,
        @Query("appid")
        appId:String = Constants.API_KEY
    ): City

}