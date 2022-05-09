package com.example.k_1919_2_1.view.details

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.k_1919_2_1.BuildConfig
import com.example.k_1919_2_1.ViewModel.ResponseState
import com.example.k_1919_2_1.ViewModel.AppState
import com.example.k_1919_2_1.databinding.FragmentDetailsBinding
import com.example.k_1919_2_1.repository.*
import com.example.k_1919_2_1.repository.dto.WeatherDTO
import com.example.k_1919_2_1.utils.*
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailsFragment : Fragment(), OnServerResponse, OnSeverResponseListener{

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get(){
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(receiver)
    }

    val receiver = object: BroadcastReceiver() {//настраиваемся на волну)
    override fun onReceive(context: Context?, intent: Intent?) {
        intent?.let { intent->
            intent.getParcelableExtra<WeatherDTO>(KEY_BUNDLE_SERVICE_BROADCAST_WEATHER)?.let {
                onResponce(it)
            }

        }
    }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    lateinit var currentCityName:String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(receiver,
            IntentFilter(KEY_WAVE_SERVICE_BROADCAST)
        )
        arguments?.getParcelable<Weather>(KEY_BUNDLE_WEATHER)?.let {
            currentCityName = it.city.name
            //Thread{
            //    WeatherLoader(this@DetailsFragment,this@DetailsFragment).loadWeather(it.city.lat,it.city.lon)
            //}.start()
//            requireActivity().startService(Intent(requireContext(),DetailService::class.java).apply {
//                putExtra(KEY_BUNDLE_LAT, it.city.lat)
//                putExtra(KEY_BUNDLE_LON, it.city.lon)
//            })
            getWeather(it.city.lat, it.city.lon)
        }
    }

    private fun getWeather(lat:Double, lon:Double){
        binding.loadingLayout.visibility = View.VISIBLE

        val client  = OkHttpClient()
        val builder = Request.Builder()

        builder.addHeader(YANDEX_API_KEY, BuildConfig.WEATHER_API_KEY)
        builder.url("$YANDEX_DOMAIN${YANDEX_ENDPOINT}lat=$lat&lon=$lon")
        val request = builder.build()
        val callback:Callback = object : Callback{
            override fun onFailure(call: Call, e: IOException) {
                //TODO HW
                //binding.loadingLayout.visibility = View.GONE
                //renderDate()

            }

            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful){
                    val weatherDTO: WeatherDTO = Gson().fromJson(response.body()!!.string(), WeatherDTO::class.java)
                    requireActivity().runOnUiThread{
                        renderDate(weatherDTO)
                    }

                }else{
                    //TODO HW
                }
            }
        }
        val call = client.newCall(request)
        Thread{
            //Работа1
            val response = call.execute() //если хотим выполнить что-то здесь и сейчас

            //работа 2 с использованием  response
        }.start()

        call.enqueue(callback)
    }

    private fun renderDate(weather: WeatherDTO){
        with(binding){
            loadingLayout.visibility = View.GONE
            cityName.text = currentCityName
            with(weather){
                temperatureValue.text = weather.factDTO.temperature.toString()
                feelsLikeValue.text = weather.factDTO.feelsLike.toString()
                cityCoordinates.text = "${weather.infoDTO.lat} ${weather.infoDTO.lon}"
            }
            Snackbar.make(mainView,"Получилось",Snackbar.LENGTH_LONG).show()
            mainView.showSnackBar() //TODO HW можно вынести в функцию - расширение
        }
        //Toast.makeText(requireContext(),"Работает", Toast.LENGTH_SHORT).show()
    }
    //TODO HW
    private fun View.showSnackBar(){

    }
    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle):DetailsFragment{
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onResponce(weatherDTO: WeatherDTO) {
            renderDate(weatherDTO)
    }



    override fun onError(error: ResponseState) {
        //TODO("Выводим ошибку")
    }
}

private fun Any.observe(viewLifecycleOwner: LifecycleOwner, observer: Observer<AppState>) {

}



