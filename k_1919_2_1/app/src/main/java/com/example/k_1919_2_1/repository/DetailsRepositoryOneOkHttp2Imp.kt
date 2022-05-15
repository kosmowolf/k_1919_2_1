package com.example.k_1919_2_1.repository

import com.example.k_1919_2_1.ViewModel.DetailsViewModel
import com.example.k_1919_2_1.utils.YANDEX_DOMAIN
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsRepositoryOneOkHttp2Imp:DetailsRepositoryOne {
    override fun getWeatherDetails(city: City, callback: DetailsViewModel.Callback) {
        val retrofit2 = Retrofit.Builder().apply {
            baseUrl(YANDEX_DOMAIN)
            addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
        }.build().create(WeatherAPI::class.java)

    }
}