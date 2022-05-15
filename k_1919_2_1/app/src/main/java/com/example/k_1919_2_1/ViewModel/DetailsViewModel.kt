package com.example.k_1919_2_1.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.k_1919_2_1.repository.*


class DetailsViewModel(
    private val liveData: MutableLiveData<DetailsState> = MutableLiveData(),

    private val repositoryAdd: DetailsRepositoryAdd = DetailsRepositoryRoomImpl()
): ViewModel() {

    private var repositoryOne: DetailsRepositoryOne = DetailsRepositoryOneRetrofit2Impl()

    fun getLiveData()=liveData

    fun getWeather(city:City){
        liveData.postValue(DetailsState.Loading)
        repositoryOne = if (isInternet()){
            DetailsRepositoryOneRetrofit2Impl()
        }else{
            DetailsRepositoryRoomImpl()
        }
            repositoryOne.getWeatherDetails(city,object : Callback{
                override fun onResponse(weather: Weather) {
                    liveData.postValue(DetailsState.Success(weather))
                    if (isInternet()){//костыль
                        repositoryAdd.addWeather(weather)
                    }

                }

                override fun onFail(){
                    //  liveData.postValue(DetailsState.Error())
                }
            })


    }

    private fun isInternet(): Boolean {
        //  Заглушка
        return true
    }

    interface Callback{
        fun  onResponse(weather: Weather)
        fun onFail()
    }


}