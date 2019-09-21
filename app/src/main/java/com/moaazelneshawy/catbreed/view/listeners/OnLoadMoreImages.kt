package com.moaazelneshawy.catbreed.view.listeners

import com.moaazelneshawy.catbreed.network.models.Breed

interface OnLoadMoreImages {
    fun loadMoreImages(breed: Breed)
}