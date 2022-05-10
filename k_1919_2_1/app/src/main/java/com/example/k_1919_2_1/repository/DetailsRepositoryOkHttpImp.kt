package com.example.k_1919_2_1.repository

import com.example.k_1919_2_1.BuildConfig
import com.example.k_1919_2_1.ViewModel.DetailsViewModel
import com.example.k_1919_2_1.repository.dto.WeatherDTO
import com.example.k_1919_2_1.utils.YANDEX_API_KEY
import com.example.k_1919_2_1.utils.YANDEX_DOMAIN
import com.example.k_1919_2_1.utils.YANDEX_ENDPOINT
import com.example.k_1919_2_1.utils.convertDTOtoModel
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request

import java.io.IOException

class DetailsRepositoryOkHttpImp:DetailsRepository {
    override fun getWeatherDetails(city: City, callback: DetailsViewModel.Callback) {
        val client  = OkHttpClient()
        val builder = Request.Builder()

        builder.addHeader(YANDEX_API_KEY, BuildConfig.WEATHER_API_KEY)
        builder.url("$YANDEX_DOMAIN${YANDEX_ENDPOINT}lat=${city.lat}&lon=${city.lon}")
        val request = builder.build()
        val call = client.newCall(request)

        Thread{
            //Работа1
            val response = call.execute() //если хотим выполнить что-то здесь и сейчас
            if(response.isSuccessful){
                val serverResponse = response.body()!!.string()
                val weatherDTO: WeatherDTO = Gson().fromJson(serverResponse, WeatherDTO::class.java)
                val weather = convertDTOtoModel(weatherDTO)
                weather.city = city
                callback.onResponse(weather)
            } else{
                //HW
            }

            //работа 2 с использованием  response
        }.start()

    }
}