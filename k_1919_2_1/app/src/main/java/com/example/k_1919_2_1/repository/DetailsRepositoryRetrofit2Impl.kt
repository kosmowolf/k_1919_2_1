package com.example.k_1919_2_1.repository

import com.example.k_1919_2_1.BuildConfig
import com.example.k_1919_2_1.ViewModel.DetailsViewModel
import com.example.k_1919_2_1.repository.dto.WeatherDTO
import com.example.k_1919_2_1.utils.YANDEX_DOMAIN
import com.example.k_1919_2_1.utils.convertDTOtoModel
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsRepositoryRetrofit2Impl:DetailsRepository {
    override fun getWeatherDetails(city: City, callbackMy: DetailsViewModel.Callback) {
        val weatherAPI =  Retrofit.Builder().apply {
            baseUrl(YANDEX_DOMAIN)
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        }.build().create(WeatherAPI::class.java)

        //val response = weatherAPI.getWeather(BuildConfig.WEATHER_API_KEY,city.lat,city.lon).execute() //можно так
        weatherAPI.getWeather(BuildConfig.WEATHER_API_KEY,city.lat,city.lon).enqueue(object : Callback<WeatherDTO>{
            override fun onResponse(call: Call<WeatherDTO>, response: Response<WeatherDTO>) {
                if(response.isSuccessful){
                    response.body()?.let{
                        callbackMy.onResponse(convertDTOtoModel(it))
                    }
                }
            }

            override fun onFailure(call: Call<WeatherDTO>, t: Throwable) {
                // HW
            }

        })
    }
}