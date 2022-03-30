package com.example.k_1919_2_1.repository

class RepositoryImplementation:Repository {
    override fun getWeatherFromServer():Weather {
        Thread.sleep(2000L) //  эмуляция запроса на сервер
        return Weather()
    }

    override fun getWeatherFromLocalStorage():Weather {
        Thread.sleep(2000L) //  эмуляция локального запроса
        return Weather()
    }
}