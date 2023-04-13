package com.example.clothessuggester.data.local

interface LocalService {
    fun chooseClothesAccordingWeatherNow(temp:Double):Int
    fun saveImageInSharedPreferences(image:Int)

}