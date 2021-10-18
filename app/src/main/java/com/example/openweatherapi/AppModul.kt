package com.example.openweatherapi

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModul {

    @Provides
    @Singleton
    fun getBaseUrl():String = "http://api.openweathermap.org/data/2.5/"

    @Provides
    @Singleton
    fun getRetrofitBuilder(baseUrl:String):Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun getApiService(retrofit: Retrofit):ApiService = retrofit.create(ApiService::class.java)

    /*
    @Provides
    @Singleton
    fun getBaseUrl(): String = "http://api.openweathermap.org/data/2.5/"

    @Provides
    @Singleton
    fun getRetrofitBuilder(baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun getApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)*/


}