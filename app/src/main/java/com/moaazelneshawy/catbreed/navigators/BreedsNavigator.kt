package com.moaazelneshawy.catbreed.navigators

import com.moaazelneshawy.catbreed.network.models.Breed

interface BreedsNavigator : BaseNavigator {

    fun onBreeds(breeds: List<Breed>)

}