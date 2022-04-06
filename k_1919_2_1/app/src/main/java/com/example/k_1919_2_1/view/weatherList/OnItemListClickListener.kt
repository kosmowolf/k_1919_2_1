package com.example.k_1919_2_1.view.weatherList

import com.example.k_1919_2_1.repository.Weather

interface OnItemListClickListener {
    fun onItemClick(weather: Weather)
}