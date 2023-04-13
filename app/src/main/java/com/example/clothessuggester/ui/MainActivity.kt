package com.example.clothessuggester.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.clothessuggester.data.local.LocalServiceImpl
import com.example.clothessuggester.databinding.ActivityMainBinding
import com.example.clothessuggester.model.WeatherData
import com.example.clothessuggester.presenter.IMainView
import com.example.clothessuggester.presenter.MainPresenter


import com.orhanobut.hawk.Hawk
import okhttp3.*
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity(), IMainView {
    lateinit var binding: ActivityMainBinding
    lateinit var localServiceImpl: LocalServiceImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val presenter = MainPresenter(this)
        presenter.getCurrentWeatherStatus()
        localServiceImpl=LocalServiceImpl()


        Hawk.init(this).build()

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getCurrentWeatherStatusSuccess(weatherResponse: WeatherData) {
        val temp = weatherResponse.main.temp
        val imageOutfit =localServiceImpl.chooseClothesAccordingWeatherNow(temp)

        localServiceImpl.saveImageInSharedPreferences(imageOutfit)

        runOnUiThread {
            showViewsInCurrentWeatherStatusSuccess(
                weatherResponse,
                imageOutfit,
                binding)
        }
    }


    override fun getCurrentWeatherStatusFailure(message: IOException) {
        runOnUiThread {
            showViewsInCurrentWeatherStatusFailure(message.toString())
        }


        }
    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.O)
    fun showViewsInCurrentWeatherStatusSuccess(
        result: WeatherData,
        imageOutfit: Int,
        binding: ActivityMainBinding
    ) {
        val temp = result.main.temp.toInt().toString()

        binding.textViewNameOfCity.text = result.name
        binding.textViewDateOfTheDay.text = LocalDate
            .now()
            .format(DateTimeFormatter.ofPattern("dd MMMM"))
            .toString()
        binding.textViewTemperature.text = "${temp}°C"
        Glide.with(binding.imageOutfit.context).load(imageOutfit).into(binding.imageOutfit)
    }
    private fun showViewsInCurrentWeatherStatusFailure(message: String) {
       Log.i("Weather",message)
    }
}