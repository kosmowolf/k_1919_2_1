package com.example.k_1919_2_1.repository

class RepositoryImplementation:Repository {
    override fun getWeatherFromServer():Weather {
        Thread.sleep(2000L) //  эмуляция запроса на сервер
        return Weather()
    }

    override fun getWorldCWeatherFromLocalStorage():List<Weather> {
        Thread.sleep(2000L) //  эмуляция локального запроса
        return getWorldCities()
    }
    override fun getRussianCitiesWeatherFromLocalStorage():List<Weather> {
        Thread.sleep(2000L) //  эмуляция локального запроса
        return getRussianCities()
    }
}