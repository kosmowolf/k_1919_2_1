package com.example.k_1919_2_1.ViewModel

import android.app.Person
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.k_1919_2_1.repository.*
import java.lang.Thread.sleep

class HistoryViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: DetailsRepositoryRoomImpl = DetailsRepositoryRoomImpl()

):ViewModel() {

    fun getData(): MutableLiveData<AppState> {
        return liveData
    }

   fun getAll(){
       repository.getAllWeatherDetails(object :CallbackForAll{
           override fun onResponse(listWeather: List<Weather>) {
               liveData.postValue(AppState.Success(listWeather))
           }

           override fun onFail() {

           }

       })
   }

    interface CallbackForAll{
        fun  onResponse(listWeather: List<Weather>)
        fun onFail()
    }
}