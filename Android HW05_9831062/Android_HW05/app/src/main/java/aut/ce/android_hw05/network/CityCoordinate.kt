package aut.ce.android_hw05.network

import com.squareup.moshi.Json

data class CityCoordinate(
    @Json(name = "lat") val lat: String,
    @Json(name = "lon") val lon: String
)

data class CityCoordinateWithCity(
    @Json(name = "lat") val lat: String,
    @Json(name = "lon") val lon: String,
    @Json(name = "city") val city: String
)
