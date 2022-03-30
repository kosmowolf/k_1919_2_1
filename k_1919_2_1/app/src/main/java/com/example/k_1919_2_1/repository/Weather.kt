package com.example.k_1919_2_1.repository

data class Weather(val city: City = getDefaultCity(),val temperature: Int=0,val feelsLike:Int=0)
fun getDefaultCity() = City("Маосква", 55.75, 37.61)

data class City(val name:String, val  lat:Double, val lon:Double)
