package com.example.clothessuggester.data.local

import com.example.clothessuggester.R
import com.orhanobut.hawk.Hawk

class LocalServiceImpl:LocalService {
    override fun chooseClothesAccordingWeatherNow(temp: Double): Int {
        val imagesCrews= intiImageCrews()
        return if (temp < 15.0) {
            imagesCrews[0].filter { it != Hawk.get(OUTFIT_IMAGE) }.random()
        } else if (temp in 15.0..25.0) {
            imagesCrews[1].filter { it != Hawk.get(OUTFIT_IMAGE) }.random()
        } else {
            imagesCrews[2].filter { it != Hawk.get(OUTFIT_IMAGE) }.random()
        }
    }

    override fun saveImageInSharedPreferences(imageOutfit:Int) {
        Hawk.put(OUTFIT_IMAGE, imageOutfit)
    }
   private fun intiImageCrews(): List<List<Int>> {
        val autumnClothes = listOf(
            R.drawable.imageaut,
            R.drawable.imageautum,
        )

        val summerClothes = listOf(
            R.drawable.imagesum,
            R.drawable.imagesumm,
        )

        val winterClothes = listOf(
            R.drawable.imagewint,
            R.drawable.imagewinter,

        )

        return listOf(winterClothes, autumnClothes, summerClothes)
    }
    companion object {
        const val OUTFIT_IMAGE = "outfit image"
    }
}
