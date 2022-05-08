package com.example.k_1919_2_1.ViewModel

import com.example.k_1919_2_1.repository.Weather

sealed class ResponseState {//запечатанный класс - предок для всех состояний нашего приложения
    object Error1:ResponseState()//прогресс
    data class Error2 (val weatherList:List<Weather>):ResponseState(){//пришел успешный ответ от сервера - там силит наша погода
        fun test(){}
    }
    data class Error3(val error: Throwable):ResponseState() //наши ошибки

}
