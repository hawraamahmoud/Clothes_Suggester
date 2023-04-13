package com.example.clothessuggester.data.remote

import com.example.clothessuggester.model.WeatherData
import java.io.IOException

interface WeatherService {
    fun getCurrentWeatherStatus(
        onGetCurrentWeatherStatusSuccess: (weatherResponse: WeatherData) -> Unit,
        onGetCurrentWeatherStatusFailure: (e: IOException) -> Unit
    )
}