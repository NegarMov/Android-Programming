package aut.ce.android_hw05.network

import com.squareup.moshi.Json

data class FullWeatherForecast(
    @Json(name = "list") val list: List<DailyForecast>
)

data class DailyForecast(
    @Json(name = "main") val mainInfo: MainForecast,
    @Json(name = "weather") val weatherInfo: List<WeatherForecast>,
    @Json(name = "dt_txt") val date: String
)

data class MainForecast(
    @Json(name = "temp") val temp: String,
    @Json(name = "temp_min") val temp_min: String,
    @Json(name = "temp_max") val temp_max: String,
    @Json(name = "humidity") val humidity: String
)

data class WeatherForecast(
    @Json(name = "main") val mainWeather: String,
    @Json(name = "description") val description: String,
    @Json(name = "icon") val icon: String
)
