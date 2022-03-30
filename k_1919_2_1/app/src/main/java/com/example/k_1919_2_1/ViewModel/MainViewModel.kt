package com.example.k_1919_2_1.ViewModel

import android.app.Person
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.k_1919_2_1.repository.Repository
import com.example.k_1919_2_1.repository.RepositoryImplementation
import java.lang.Thread.sleep

class MainViewModel(
    private val liveData: MutableLiveData<AppState> = MutableLiveData(),
    private val repository: Repository = RepositoryImplementation()
):ViewModel() {

    fun getData(): MutableLiveData<AppState> {
        return liveData
    }

    fun getWeather(){
        Thread{
            liveData.postValue(AppState.Loading)

            if ((0..10).random() > 5)
                liveData.postValue(AppState.Success(repository.getWeatherFromLocalStorage()))
            else
                liveData.postValue(AppState.Error(IllegalAccessException()))
        }.start()
    }
}