package com.moaazelneshawy.catbreed.network.models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.lang.ref.WeakReference

data class Breed(

    @SerializedName("alt_names") val alt_names: String,
    @SerializedName("temperament") val temperament: String,
    @SerializedName("life_span") val life_span: String,
    @SerializedName("weight_imperial") val weight_imperial: String,
    @SerializedName("wikipedia_url") val wikipedia_url: String,
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: String,
    @SerializedName("origin") val origin: String,
    @SerializedName("reference_image_id") val reference_image_id: String,
    @SerializedName("country_code") val country_code: String,

    @SerializedName("experimental") val experimental: Int,
    @SerializedName("adaptability") val adaptability: Int,
    @SerializedName("hairless") val hairless: Int,
    @SerializedName("hypoallergenic") val hypoallergenic: Int,
    @SerializedName("natural") val natural: Int,
    @SerializedName("rare") val rare: Int,
    @SerializedName("rex") val rex: Int,
    @SerializedName("short_legs") val short_legs: Int,
    @SerializedName("suppressed_tail") val suppressed_tail: Int
) : Serializable

data class ImagesResponse(
    @SerializedName("url") val url: String,
    @SerializedName("id") val id: String,
    @SerializedName("breeds") val breeds: List<Breed>
)
