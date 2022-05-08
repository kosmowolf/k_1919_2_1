package com.example.k_1919_2_1.repository

import android.os.Handler
import android.os.Looper
import com.example.k_1919_2_1.BuildConfig
import com.example.k_1919_2_1.ViewModel.ResponseState
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class WeatherLoader(private val onServerResponseListener: OnServerResponse,
                    private val onSeverResponseListener: OnSeverResponseListener) {

    fun loadWeather(lat:Double,lon:Double){
        val urlText = "https://api.weather.yandex.ru/v2/informers?lat=$lat&lon=$lon"
        //val urlText = "http://212.86.114.27/v2/informers?lat=$lat&lon=$lon"
        //binding.webview.loadUrl(urlText)
        val uri = URL(urlText)

       // repeat(100) {
            Thread {
                val  urlConnection: HttpURLConnection = (uri.openConnection() as HttpURLConnection).apply {
                    connectTimeout = 1000 //set
                    //val r = readTimeout  //get
                    readTimeout= 1000 //set
                    addRequestProperty("X-Yandex-API-Key", BuildConfig.WEATHER_API_KEY)
                }
                Thread.sleep(500)
                //try {
                val headers = urlConnection.headerFields
                val responseCode = urlConnection.responseCode
                val responseMessage = urlConnection.responseMessage

                val serverside = 500
                val clientside = 400..serverside
                val responseOk = 200..299


                if (responseCode >= serverside) {
                    //todo HW "Что-то пошло не так" на стороне сервера
                    onSeverResponseListener.onError(ResponseState.Error1)
                } else if (responseCode in clientside) {
                    //todo HW "Что-то пошло не так" на стороне клиента
                } else if (responseCode in responseOk) {
                    val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream))
                    // val result = (buffer.toString())
                    val weatherDTO: WeatherDTO = Gson().fromJson(buffer, WeatherDTO::class.java)
                    Handler(Looper.getMainLooper()).post {// ящик на конвеере
                        onServerResponseListener.onResponce(weatherDTO)
                    }
                }

                //закрытие потока
            }.start()
        //}
    }
}