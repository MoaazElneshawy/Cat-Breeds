package com.moaazelneshawy.catbreed.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.moaazelneshawy.catbreed.navigators.BreedDetailsNavigator
import com.moaazelneshawy.catbreed.navigators.BreedsNavigator
import com.moaazelneshawy.catbreed.network.retrofit.RetrofitBuilder
import com.moaazelneshawy.catbreed.repository.BreedsRepo

class BreedDetailsViewModel(application: Application) :
    BaseViewModel<BreedDetailsNavigator>(application) {

    private val TAG = BreedDetailsViewModel::class.java.simpleName
    private var repo: BreedsRepo

    init {
        repo = BreedsRepo()
    }


    fun getImage(owner: LifecycleOwner, breeId: String) {
        val date = repo.getImage(breeId)
        date.observe(owner, Observer {
            if (it.data?.url != null) {
                navigator?.onLoadImage(it.data.url)
            }
            navigator?.onFail(it.fail)
            navigator?.onLoading(it.isLoading)
            Log.e(TAG, "url ${it.data?.url}")
            if (it.data?.breeds != null) navigator?.onLoadBreed(it.data.breeds.get(0))
        })
    }

}