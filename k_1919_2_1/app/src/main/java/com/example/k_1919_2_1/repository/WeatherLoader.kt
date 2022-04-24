package com.example.k_1919_2_1.repository

import android.os.Handler
import android.os.Looper
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class WeatherLoader(private val onServerResponseListener: OnServerResponse) {

    fun loadWeather(lat:Double,lon:Double){
        val urlText = "https://api.weather.yandex.ru/v2/informers?lat=$lat&lon=$lon"
        //binding.webview.loadUrl(urlText)
        val uri = URL(urlText)
        val  urlConnection: HttpsURLConnection = (uri.openConnection() as HttpsURLConnection).apply {
            connectTimeout = 1000 //set
            //val r = readTimeout  //get
            readTimeout= 1000 //set
            addRequestProperty("X-Yandex-API-Key","29a3e2c5-294a-4981-88b5-2c556815c617")
        }
        Thread{
            try {


                val headers = urlConnection.headerFields
                val responseCode = urlConnection.responseCode
                val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream))
                // val result = (buffer.toString())

                val weatherDTO:WeatherDTO = Gson().fromJson(buffer,WeatherDTO::class.java)
                Handler(Looper.getMainLooper()).post { onServerResponseListener.onResponce(weatherDTO) }
            } catch (e: JsonSyntaxException){
                //todo отправить на сервер ошибку
                //todo HW "Что-то пошло не так"
                //Snackbar.make(mainView, "Что-то пошло не так", Snackbar.LENGTH_LONG).show()
            }
            finally {
                urlConnection.disconnect()
            }



        }.start()
    }
}