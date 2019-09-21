package com.moaazelneshawy.catbreed.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.moaazelneshawy.catbreed.navigators.BreedsNavigator
import com.moaazelneshawy.catbreed.network.models.Breed
import com.moaazelneshawy.catbreed.network.retrofit.ApiInterfaces
import com.moaazelneshawy.catbreed.network.retrofit.RetrofitBuilder
import com.moaazelneshawy.catbreed.repository.BreedsRepo
import com.moaazelneshawy.catbreed.repository.BreedsUiState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BreedsViewModel(application: Application) : BaseViewModel<BreedsNavigator>(application) {

    private val breedsRepo: BreedsRepo
    private val TAG = BreedsViewModel::class.java.simpleName

    init {
        breedsRepo = BreedsRepo()
    }

    private lateinit var breedsLD: LiveData<BreedsUiState>


    fun getBreeds(owner: LifecycleOwner) {
        breedsLD = breedsRepo.getBreeds()
        breedsLD.observe(owner, Observer {
            it?.let {
                if (!it.breeds.isNullOrEmpty()) {
                    navigator?.onBreeds(it.breeds)
                }
                navigator?.onLoading(it.isLoading)
                navigator?.onFail(it.fail)

            }
        })
    }

}

