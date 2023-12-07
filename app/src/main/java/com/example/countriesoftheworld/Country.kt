package com.example.countriesoftheworld

data class Country(
    val name: countryName,
    val capital: List<String?>,
    val population: Long,
    val area: Long,
    val languages: Map<String, String>,
    val flags: flags
)

data class countryName(
    val common: String
)

data class flags(
    val svg: String
)