package com.example.k_1919_2_1.repository

import com.example.k_1919_2_1.ViewModel.DetailsViewModel
import com.example.k_1919_2_1.ViewModel.HistoryViewModel


interface DetailsRepositoryAll {
    fun getAllWeatherDetails(callback: HistoryViewModel.CallbackForAll)
}