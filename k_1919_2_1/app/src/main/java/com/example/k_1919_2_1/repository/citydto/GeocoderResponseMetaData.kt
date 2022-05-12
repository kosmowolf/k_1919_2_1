package com.example.k_1919_2_1.repository.citydto

data class GeocoderResponseMetaData(
    val Point: PointX,
    val found: String,
    val request: String,
    val results: String
)