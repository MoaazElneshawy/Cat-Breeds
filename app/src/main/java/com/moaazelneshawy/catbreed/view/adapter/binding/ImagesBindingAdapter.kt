package com.moaazelneshawy.catbreed.view.adapter.binding

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.moaazelneshawy.catbreed.R
import com.squareup.picasso.Picasso

class ImagesBindingAdapter {
    companion object {
        @BindingAdapter(value = ["image_src"])
        @JvmStatic
        fun bindImage(imageView: AppCompatImageView, image_src: String?) {
            Picasso.with(imageView.context)
                .load(image_src)
                .placeholder(R.drawable.ic_placeholder)
                .into(imageView)
        }
    }
}