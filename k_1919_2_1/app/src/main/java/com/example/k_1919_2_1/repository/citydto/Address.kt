package com.example.k_1919_2_1.repository.citydto

data class Address(
    val Components: List<Component>,
    val country_code: String,
    val formatted: String,
    val postal_code: String
)