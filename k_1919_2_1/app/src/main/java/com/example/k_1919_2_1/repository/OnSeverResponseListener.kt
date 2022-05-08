package com.example.k_1919_2_1.repository

import com.example.k_1919_2_1.ViewModel.ResponseState

fun interface OnSeverResponseListener {
    fun onError(error: ResponseState)
}