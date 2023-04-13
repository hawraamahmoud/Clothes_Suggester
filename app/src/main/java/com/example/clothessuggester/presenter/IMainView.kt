package com.example.clothessuggester.presenter

import com.example.clothessuggester.model.WeatherData
import java.io.IOException

interface IMainView {

    fun getCurrentWeatherStatusSuccess(weatherResponse: WeatherData)
    fun getCurrentWeatherStatusFailure(e: IOException)
}