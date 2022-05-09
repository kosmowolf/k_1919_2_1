package com.example.k_1919_2_1.view.details

import android.app.IntentService
import android.content.Intent
import android.util.Log
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.k_1919_2_1.BuildConfig
import com.example.k_1919_2_1.repository.dto.WeatherDTO
import com.example.k_1919_2_1.utils.*
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class DetailService (val name:String=""): IntentService(name) {
    override fun onHandleIntent(intent: Intent?) {
        Log.d("@@@","work DetailService")
        intent?.let{
            val lat = it.getDoubleExtra(KEY_BUNDLE_LAT,0.0)
            val lon = it.getDoubleExtra(KEY_BUNDLE_LON, 0.0)
            Log.d("@@@", "work DetailService $lat $lon")

            val urlText = "$YANDEX_DOMAIN${YANDEX_ENDPOINT}lat=$lat&lon=$lon"
            //val urlText = "http://212.86.114.27/v2/informers?lat=$lat&lon=$lon"

            val uri = URL(urlText)

                val  urlConnection: HttpURLConnection = (uri.openConnection() as HttpURLConnection).apply {
                    connectTimeout = 1000 //set
                    readTimeout= 1000 //set
                    addRequestProperty(YANDEX_API_KEY, BuildConfig.WEATHER_API_KEY)
                }


            //Thread.sleep(500)

            val headers = urlConnection.headerFields
            val responseCode = urlConnection.responseCode
            val responseMessage = urlConnection.responseMessage

            val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream))
            val weatherDTO: WeatherDTO = Gson().fromJson(buffer, WeatherDTO::class.java)

            val message = Intent(KEY_WAVE_SERVICE_BROADCAST)
            message.putExtra(
                KEY_BUNDLE_SERVICE_BROADCAST_WEATHER, weatherDTO
            )
            sendBroadcast(message)
            LocalBroadcastManager.getInstance(this).sendBroadcast(message)
        }
    }

}