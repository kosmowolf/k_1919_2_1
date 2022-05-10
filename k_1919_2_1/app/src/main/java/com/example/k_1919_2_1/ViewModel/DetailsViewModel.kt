package com.example.k_1919_2_1.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.k_1919_2_1.repository.*


class DetailsViewModel(
    private val liveData: MutableLiveData<DetailsState> = MutableLiveData(),
    private var repository: DetailsRepository = DetailsRepositoryRetrofit2Impl()
): ViewModel() {
    fun getLiveData()=liveData

    fun getWeather(city:City){
        liveData.postValue(DetailsState.Loading)
        repository.getWeatherDetails(city,object : Callback{
            override fun onResponse(weather: Weather) {
                liveData.postValue(DetailsState.Success(weather))
            }

            override fun onFail(){
              //  liveData.postValue(DetailsState.Error())
            }
        })
    }
    interface Callback{
        fun  onResponse(weather: Weather)
        fun onFail()
    }

}