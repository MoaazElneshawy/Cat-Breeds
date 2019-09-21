package com.moaazelneshawy.catbreed.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.moaazelneshawy.catbreed.network.models.Breed
import com.moaazelneshawy.catbreed.network.models.ImagesResponse
import com.moaazelneshawy.catbreed.network.retrofit.ApiInterfaces
import com.moaazelneshawy.catbreed.network.retrofit.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BreedsRepo {
    private val TAG = BreedsRepo::class.java.simpleName
    private val retrofitBuilder: RetrofitBuilder

    init {
        retrofitBuilder = RetrofitBuilder()
    }

    fun getBreeds(): LiveData<BreedsUiState> {
        val breedsLiveData = MutableLiveData<BreedsUiState>()
        breedsLiveData.value = BreedsUiState(false, true, null)
        val call = retrofitBuilder.createService(ApiInterfaces::class.java).getBreads()

        call.enqueue(object : Callback<List<Breed>> {
            override fun onFailure(call: Call<List<Breed>>, t: Throwable) {
                breedsLiveData.value = BreedsUiState(true, false, null)
            }

            override fun onResponse(call: Call<List<Breed>>, response: Response<List<Breed>>) {
                if (response.isSuccessful)
                    breedsLiveData.value = BreedsUiState(false, false, response.body())
                else breedsLiveData.value = BreedsUiState(true, false, null)
            }
        })

        return breedsLiveData
    }


    fun getImage(id: String, limit: Int = 0): LiveData<BreedDetailsUiState> {
        val imageLiveData = MutableLiveData<BreedDetailsUiState>()
        imageLiveData.value = BreedDetailsUiState(false, true, null)
        val call = retrofitBuilder.createService(ApiInterfaces::class.java).getImage(id, limit)
        call.enqueue(object : Callback<List<ImagesResponse>> {
            override fun onFailure(call: Call<List<ImagesResponse>>, t: Throwable) {
                imageLiveData.value = BreedDetailsUiState(true, false, null)
            }

            override fun onResponse(
                call: Call<List<ImagesResponse>>,
                response: Response<List<ImagesResponse>>
            ) {
                if (response.isSuccessful) {
                    imageLiveData.value = BreedDetailsUiState(false, false, response.body()?.get(0))
                } else imageLiveData.value = BreedDetailsUiState(true, false, null)
            }

        })
        return imageLiveData
    }

    fun getImages(id: String, limit: Int): LiveData<BreedImagesUiState> {
        val imageLiveData = MutableLiveData<BreedImagesUiState>()
        imageLiveData.value = BreedImagesUiState(false, true, null)
        val call = retrofitBuilder.createService(ApiInterfaces::class.java).getImage(id, limit)
        call.enqueue(object : Callback<List<ImagesResponse>> {
            override fun onFailure(call: Call<List<ImagesResponse>>, t: Throwable) {
                imageLiveData.value = BreedImagesUiState(true, false, null)
                Log.e(TAG, "getImages f ${t.message} - ${call.request().url()}")
            }

            override fun onResponse(
                call: Call<List<ImagesResponse>>,
                response: Response<List<ImagesResponse>>
            ) {
                Log.e(TAG, "getImages s ${response.isSuccessful} - ${call.request().url()}")
                if (response.isSuccessful) {
                    imageLiveData.value = BreedImagesUiState(false, false, response.body())
                } else imageLiveData.value = BreedImagesUiState(true, false, null)
            }

        })
        return imageLiveData
    }

}


data class BreedsUiState(
    val fail: Boolean,
    val isLoading: Boolean,
    val breeds: List<Breed>?
)

data class BreedDetailsUiState(
    val fail: Boolean,
    val isLoading: Boolean,
    val data: ImagesResponse?
)

data class BreedImagesUiState(
    val fail: Boolean,
    val isLoading: Boolean,
    val data: List<ImagesResponse>?
)
