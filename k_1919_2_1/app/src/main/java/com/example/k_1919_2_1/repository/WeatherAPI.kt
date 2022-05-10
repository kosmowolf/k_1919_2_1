package com.example.k_1919_2_1.repository

import com.example.k_1919_2_1.repository.dto.WeatherDTO
import com.example.k_1919_2_1.utils.YANDEX_API_KEY
import com.example.k_1919_2_1.utils.YANDEX_ENDPOINT
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface WeatherAPI {
    @GET(YANDEX_ENDPOINT) //Только endpoint
    fun getWeather(
        @Header(YANDEX_API_KEY) apikey:String,
        @Query("lat") lat:Double,
        @Query("lon") lon: Double
    ): Call<WeatherDTO>

}