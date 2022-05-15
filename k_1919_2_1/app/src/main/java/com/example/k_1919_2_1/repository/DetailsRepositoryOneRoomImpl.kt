package com.example.k_1919_2_1.repository

import com.example.k_1919_2_1.MyApp
import com.example.k_1919_2_1.ViewModel.DetailsViewModel
import com.example.k_1919_2_1.ViewModel.HistoryViewModel
import com.example.k_1919_2_1.utils.convertHistoryEntityToWeather
import com.example.k_1919_2_1.utils.convertWeatherToEntity

class DetailsRepositoryRoomImpl:DetailsRepositoryOne,DetailsRepositoryAll,DetailsRepositoryAdd {
    override fun getAllWeatherDetails(callback: HistoryViewModel.CallbackForAll) {
         callback.onResponse(convertHistoryEntityToWeather(MyApp.getHistoryDao().getAll()))
    }

    override fun getWeatherDetails(city: City, callback: DetailsViewModel.Callback) {
        val list = convertHistoryEntityToWeather(MyApp.getHistoryDao().getHistoryForCity(city.name))
        if (list.isEmpty()){
            callback.onFail()
        }else{
            callback.onResponse(list.last())
        }

    }

    override fun addWeather(weather: Weather) {
        MyApp.getHistoryDao().insert(convertWeatherToEntity(weather))
    }

}