package com.moaazelneshawy.catbreed.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.moaazelneshawy.catbreed.navigators.BreedsImagesNavigator
import com.moaazelneshawy.catbreed.repository.BreedImagesUiState
import com.moaazelneshawy.catbreed.repository.BreedsRepo
import com.moaazelneshawy.catbreed.repository.BreedsUiState

class BreedsImagesViewModel(application: Application) :
    BaseViewModel<BreedsImagesNavigator>(application) {


    private val breedsRepo: BreedsRepo
    private val TAG = BreedsImagesViewModel::class.java.simpleName

    init {
        breedsRepo = BreedsRepo()
    }

    private lateinit var breeds: LiveData<BreedImagesUiState>

    fun getImages(lifeCycle: LifecycleOwner, breedId: String, limit: Int) {
        val urls = ArrayList<String>()
        breeds = breedsRepo.getImages(breedId, limit)
        breeds.observe(lifeCycle, Observer {
            it?.let {
                navigator?.onFail(it.fail)
                navigator?.onLoading(it.isLoading)
                if (it.data != null) {
                    val tt = it.data.listIterator()
                    for (item in tt) {
                        val url = item.url
                        urls.add(url)
                    }
                    navigator?.onImagesLoad(urls)

                }
            }
        })
    }

}