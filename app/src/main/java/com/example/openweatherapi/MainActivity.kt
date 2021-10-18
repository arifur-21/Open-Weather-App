package com.example.openweatherapi

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.openweatherapi.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.text.SimpleDateFormat
import java.time.*
import java.util.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var textView: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
       setContentView(binding.root)
       /* val view = binding.root
        setContentView(view)*/



        weatherViewModel.getCityData()
        initListener()
        weatherViewModel.weatherResponse.observe(this, Observer { response ->

            

            if (response.weather[0].description == "clear sky" || response.weather[0].description == "mist") {
                Glide.with(this)
                    .load(R.drawable.clouds)
                    .into(binding.image)
            } else
                if (response.weather[0].description == "haze" || response.weather[0].description == "overcast clouds" || response.weather[0].description == "fog") {
                    Glide.with(this)
                        .load(R.drawable.haze)
                        .into(binding.image)
                } else
                    if (response.weather[0].description == "rain") {
                        Glide.with(this)
                            .load(R.drawable.rain)
                            .into(binding.image)
                    }


            binding.apply {

                sunriceId.text = response.sys.sunrise.toString()
                sunsetId.text = response.sys.sunset.toString()
                windId.text = response.wind.deg.toString()
                pressreId.text = response.main.pressure.toString()
                humidityId.text = response.main.humidity.toString()
                temMinId.text = response.main.temp_min.toString()
                maxTempId.text = response.main.temp_max.toString()
                tempId.text = response.main.temp.toString()
                binding.updatedAt.text = response.dt.toString()
                statusId.text = response.weather[0].description
                addressId.text = response.name

            }



        })
    }


    @ExperimentalCoroutinesApi
    private fun initListener()
    {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { weatherViewModel.setSearchQuery(it) }
                Log.d("main", "onQueryTextChange: $query")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }

        })
    }


}