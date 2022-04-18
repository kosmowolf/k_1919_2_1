package com.example.k_1919_2_1.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.k_1919_2_1.R
import com.example.k_1919_2_1.databinding.ActivityMainWebViewBinding
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import java.util.stream.Collector
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class MainActivityWebView : AppCompatActivity() {
    lateinit var binding: ActivityMainWebViewBinding
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ok.setOnClickListener{
            val urlText = binding.etTextUrl.text.toString()
            //binding.webview.loadUrl(urlText)
            val uri = URL(urlText)
            val  urlConnection:HttpsURLConnection = (uri.openConnection() as HttpsURLConnection).apply {
                connectTimeout = 1000 //set
                //val r = readTimeout  //get
                readTimeout= 1000 //set
            }

            Thread{
                val headers = urlConnection.headerFields
                val buffer = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val result = getLinesAsOneBigString(buffer)
//                runOnUiThread{ //1 способ
//                    binding.webview.loadData(result,"tet/html; utf-8", "utf-8")
//                }
                Handler(Looper.getMainLooper()).post { //2 способ
                    //binding.webview.loadData(result,"tet/html; utf-8", "utf-8")
                    binding.webview.settings.javaScriptEnabled = true
                    binding.webview.loadDataWithBaseURL(null, result,"tet/html; utf-8", "utf-8",null)

                }
            }.start()

        }


    }

    fun getLinesAsOneBigString(bufferedReader:BufferedReader):String{
        return  bufferedReader.lines().collect(Collectors.joining("\n"))
    }
}