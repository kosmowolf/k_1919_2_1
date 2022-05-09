package com.example.k_1919_2_1.repository

import com.example.k_1919_2_1.repository.dto.WeatherDTO

fun interface OnServerResponse {
    fun onResponce(weatherDTO: WeatherDTO)
}