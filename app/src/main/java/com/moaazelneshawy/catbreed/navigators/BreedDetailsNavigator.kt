package com.moaazelneshawy.catbreed.navigators

import com.moaazelneshawy.catbreed.network.models.Breed

interface BreedDetailsNavigator : BaseNavigator {
    fun onLoadImage(url : String?)
    fun onLoadBreed(breed : Breed)
}