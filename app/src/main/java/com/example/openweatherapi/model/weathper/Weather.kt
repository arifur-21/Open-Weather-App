package com.example.openweatherapi.model.weathper

data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)