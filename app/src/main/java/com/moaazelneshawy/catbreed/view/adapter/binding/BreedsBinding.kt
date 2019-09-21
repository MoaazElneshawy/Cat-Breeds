package com.moaazelneshawy.catbreed.view.adapter.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moaazelneshawy.catbreed.network.models.Breed
import com.moaazelneshawy.catbreed.view.adapter.normal.BreedsAdapter
import com.moaazelneshawy.catbreed.view.listeners.OnBreedsItemClickListener

class BreedsBinding {
    companion object {
        @BindingAdapter(
            value = ["breeds_list", "on_bread_click"],
            requireAll = false
        )
        @JvmStatic
        fun bindBreeds(
            recyclerView: RecyclerView,
            breeds: List<Breed>?,
            onBreedsClick: OnBreedsItemClickListener?
        ) {

            if (breeds == null) return

            var layoutManager = recyclerView.layoutManager
            if (layoutManager == null) {
                layoutManager = LinearLayoutManager(recyclerView.context)
            }

            var adapter = recyclerView.adapter
            if (adapter == null) {
                adapter = BreedsAdapter(breeds, onBreedsClick)
            }

            recyclerView.apply {
                this.layoutManager = layoutManager
                this.adapter = adapter
            }

        }
    }

}