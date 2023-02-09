package aut.ce.android_hw05.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import aut.ce.android_hw05.network.DailyForecast
import aut.ce.android_hw05.network.LocationApi
import kotlinx.coroutines.launch
import aut.ce.android_hw05.network.WeatherApi

class WeatherViewModel : ViewModel() {

    private val _status = MutableLiveData(Status.IDLE)
    private var _lat: String = ""
    private var _lon: String = ""
    private lateinit var _forecasts: List<DailyForecast>
    private var _useCurrentLocation = false
    private var _cityName: String? = null

    val status: LiveData<Status> = _status
    val forecasts: List<DailyForecast> get() = _forecasts
    val cityName: String? get() = _cityName

    fun updateWeatherInfo(useCurrentLocation: Boolean, cityName: String? = null) {
        viewModelScope.launch {
            _useCurrentLocation = useCurrentLocation
            _status.value = Status.LOADING

            if (useCurrentLocation)
                getCurrentCoordinate()
            else if (cityName != null) {
                _cityName = cityName
                getCityCoordinate(cityName)
            }

            getWeather()
        }
    }

    fun refresh() {
        _status.value = Status.IDLE
        updateWeatherInfo(_useCurrentLocation, _cityName)
    }

    private suspend fun getWeather() {
        try {
            val listResult = WeatherApi.retrofitService.getWeather(_lat, _lon)
            _forecasts = listResult.list
            _status.value = Status.SUCCESS
        } catch (e: Exception) {
            _status.value = Status.FAILED
            Log.e("Weather View Model", e.stackTraceToString())
        }
    }

    private suspend fun getCurrentCoordinate() {
        try {
            val result = LocationApi.retrofitService.getCityCoordinate()
            _lat = result.lat
            _lon = result.lon
            _cityName = result.city
        } catch (e: Exception) {
            _status.value = Status.FAILED
            Log.e("Weather View Model", e.stackTraceToString())
        }
    }

    private suspend fun getCityCoordinate(cityName: String) {
        try {
            val listResult = WeatherApi.retrofitService.getCityCoordinate(cityName)
            _lat = listResult[0].lat
            _lon = listResult[0].lon
        } catch (e: Exception) {
            _status.value = Status.FAILED
            Log.e("Weather View Model", e.stackTraceToString())
        }
    }
}

enum class Status {
    IDLE,
    LOADING,
    SUCCESS,
    FAILED
}