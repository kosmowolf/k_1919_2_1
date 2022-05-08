package com.example.k_1919_2_1.view.details

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.*
import com.example.k_1919_2_1.ViewModel.ResponseState
import com.example.k_1919_2_1.ViewModel.AppState
import com.example.k_1919_2_1.databinding.FragmentDetailsBinding
import com.example.k_1919_2_1.repository.*
import com.example.k_1919_2_1.utils.KEY_BUNDLE_WEATHER
import com.google.android.material.snackbar.Snackbar

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class DetailsFragment() : Fragment(), OnServerResponse, OnSeverResponseListener, Parcelable {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding
        get(){
            return _binding!!
        }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    lateinit var currentCityName:String

    //constructor(parcel: Parcel) : this() {
    //    currentCityName = parcel.readString()
    //}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<Weather>(KEY_BUNDLE_WEATHER)?.let {
            currentCityName = it.city.name
            //Thread{
                WeatherLoader(this@DetailsFragment,this@DetailsFragment).loadWeather(it.city.lat,it.city.lon)
            //}.start()

        }

        //val weather:Weather = requireArguments().getParcelable<Weather>(KEY_BUNDLE_WEATHER)!!
       // renderDate(weather)

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

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(currentCityName)
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun onError(error: ResponseState) {
        //TODO("Выводим ошибку")
    }
}

private fun Any.observe(viewLifecycleOwner: LifecycleOwner, observer: Observer<AppState>) {

}



