package com.example.k_1919_2_1.ViewModel

import com.example.k_1919_2_1.repository.Weather

sealed class AppState {//запечатанный класс - предок для всех состояний нашего приложения
    object Loading:AppState()//прогресс
    data class Success (val weatherData:Weather):AppState(){//пришел успешный ответ от сервера - там силит наша погода
        fun test(){}
    }
    data class Error(val error: Throwable):AppState() //наши ошибки

}
