package com.moaazelneshawy.catbreed.network.retrofit

import com.moaazelneshawy.catbreed.network.models.Breed
import com.moaazelneshawy.catbreed.network.models.ImagesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterfaces {

    @GET("breeds")
    fun getBreads(): Call<List<Breed>>

    @GET("images/search")
    fun getImage(@Query("breed_id") breed_id: String,@Query("limit") limit : Int): Call<List<ImagesResponse>>
}