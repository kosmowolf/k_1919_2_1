package com.example.k_1919_2_1.repository

import com.example.k_1919_2_1.repository.citydto.GeoObjectCollection
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CityAPI {
    @GET("1.x/") //Только endpoint
    fun getCity(
        @Header("apikey") apikey:String="7d437612-78d3-42d2-91a1-8da53abb15b2",
        @Query("geocode") geocode:String,
        @Query("format") format: String = "json"
    ): Call<GeoObjectCollection>

}