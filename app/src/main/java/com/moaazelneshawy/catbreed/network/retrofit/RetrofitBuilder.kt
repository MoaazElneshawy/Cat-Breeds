package com.moaazelneshawy.catbreed.network.retrofit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitBuilder {

    private val BASE_URL = "https://api.thecatapi.com/v1/"
    private val gson = GsonBuilder().setLenient().create()
    private val httpClient = OkHttpClient.Builder()
        .readTimeout(120, TimeUnit.SECONDS)
        .connectTimeout(120, TimeUnit.SECONDS)
        .writeTimeout(120, TimeUnit.SECONDS)

    private val retrofitBuilder =
        Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create(gson)
        )

    private lateinit var retrofit: Retrofit

    fun <S> createService(serviceClass: Class<S>): S {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        httpClient.addInterceptor { chain ->
            val lOriginalRequest = chain.request()
            val lRequest = lOriginalRequest.newBuilder()
                .header("x-api-key", "096a81bc-a8ea-4578-b6d7-f026f225ecbf")
                .method(lOriginalRequest.method(), lOriginalRequest.body()).build()
            chain.proceed(lRequest)
        }

        val lClient = httpClient.build()
        retrofit = retrofitBuilder.client(lClient).build()
        return retrofit.create(serviceClass)
    }

}