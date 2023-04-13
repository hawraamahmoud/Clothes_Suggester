package com.example.clothessuggester.presenter

import com.example.clothessuggester.data.remote.WeatherServiceImplement
import com.example.clothessuggester.model.WeatherData
import java.io.IOException

class MainPresenter (private var iMainView: IMainView){
    private val weatherServiceImpl = WeatherServiceImplement()

    fun getCurrentWeatherStatus() =
        weatherServiceImpl.getCurrentWeatherStatus(
            ::onGetCurrentWeatherStatusSuccess,
            ::onGetCurrentWeatherStatusFailure
        )


    private fun onGetCurrentWeatherStatusSuccess(weatherResponse: WeatherData) =
        iMainView.getCurrentWeatherStatusSuccess(weatherResponse)


    private fun onGetCurrentWeatherStatusFailure(message: IOException) =
        iMainView.getCurrentWeatherStatusFailure(message)
}