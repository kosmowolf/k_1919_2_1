package com.example.k_1919_2_1.repository

import com.example.k_1919_2_1.ViewModel.DetailsViewModel


interface DetailsRepository {
    fun getWeatherDetails(city: City,callback: DetailsViewModel.Callback)
}