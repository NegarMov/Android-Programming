package aut.ce.android_hw05.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.openweathermap.org"
private const val APIKey = "a9bd8bf3bd572447f19a34ac6102b4eb"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface WeatherApiService {
    @GET("geo/1.0/direct")
    suspend fun getCityCoordinate(@Query("q") cityName : String,
                                  @Query("appid") key : String = APIKey): List<CityCoordinate>

    @GET("data/2.5/forecast")
    suspend fun getWeather(@Query("lat") lat : String,
                           @Query("lon") lon : String,
                           @Query("units") unit : String = "metric",
                           @Query("appid") key : String = APIKey) : FullWeatherForecast
}

object WeatherApi {
    val retrofitService : WeatherApiService by lazy {
        retrofit.create(WeatherApiService::class.java)
    }
}