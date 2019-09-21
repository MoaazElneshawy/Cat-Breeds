package com.moaazelneshawy.catbreed.view.adapter.binding

import android.view.View
import androidx.databinding.BindingAdapter
import com.daimajia.slider.library.Animations.DescriptionAnimation
import com.daimajia.slider.library.SliderLayout
import com.daimajia.slider.library.SliderTypes.BaseSliderView
import com.daimajia.slider.library.SliderTypes.DefaultSliderView

class SliderImagesBinding {
    companion object {
        @JvmStatic
        @BindingAdapter("imgs")
        fun bindSliderImages(slider: SliderLayout, imgs: List<String>?) {
            if (imgs != null) {
                for (img in imgs) {
                    val sliderView = DefaultSliderView(slider.getContext())
                    sliderView
                        .image(img)
                        .setScaleType(BaseSliderView.ScaleType.Fit)

                    slider.addSlider(sliderView);
                    slider.setPresetTransformer(SliderLayout.Transformer.Foreground2Background)
                    slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom)
                    slider.setTextAlignment(View.TEXT_ALIGNMENT_CENTER)
                    slider.setSoundEffectsEnabled(true)
                    slider.setCustomAnimation(DescriptionAnimation())
                    slider.setDuration(4000)
                }


            }
        }
    }
}

