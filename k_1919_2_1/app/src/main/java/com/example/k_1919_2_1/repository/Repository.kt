package com.example.k_1919_2_1.repository

interface Repository {
    fun  getWeatherFromServer():Weather
    fun getWorldCWeatherFromLocalStorage():List<Weather>
    fun getRussianCitiesWeatherFromLocalStorage():List<Weather>
}
