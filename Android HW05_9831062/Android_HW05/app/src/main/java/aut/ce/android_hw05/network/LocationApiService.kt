package aut.ce.android_hw05.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "http://ip-api.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface LocationApiService {
    @GET("json")
    suspend fun getCityCoordinate(): CityCoordinateWithCity
}

object LocationApi {
    val retrofitService : LocationApiService by lazy {
        retrofit.create(LocationApiService::class.java)
    }
}