package com.example.k_1919_2_1.ViewModel

import com.example.k_1919_2_1.repository.Weather

sealed class DetailsState {//запечатанный класс - предок для всех состояний нашего приложения
    object Loading:DetailsState()//прогресс
    data class Success (val weather:Weather):DetailsState()//пришел успешный ответ от сервера - там силит наша погода

    data class Error(val error: Throwable):DetailsState() //наши ошибки

}
