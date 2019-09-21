package com.moaazelneshawy.catbreed.view.adapter.normal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.moaazelneshawy.catbreed.R
import com.moaazelneshawy.catbreed.databinding.ItemBreadBinding
import com.moaazelneshawy.catbreed.network.models.Breed
import com.moaazelneshawy.catbreed.view.listeners.OnBreedsItemClickListener

class BreedsAdapter(
    val breeds: List<Breed>?,
    val onBreedClick: OnBreedsItemClickListener?
) :
    RecyclerView.Adapter<BreedsAdapter.BreedsViewHolder>() {

    private val TAG = BreedsAdapter::class.java.simpleName

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BreedsViewHolder {
        val itemBreadBinding =
            DataBindingUtil.inflate<ItemBreadBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_bread, parent, false
            )
        return BreedsViewHolder(itemBreadBinding.root)
    }

    override fun getItemCount(): Int {
        return breeds?.size!!
    }

    override fun onBindViewHolder(holder: BreedsViewHolder, position: Int) {
        val breed = breeds?.get(position)
        holder.itemBreadBinding?.breed = breed
        holder.itemBreadBinding?.onBreedClick = onBreedClick

    }

    class BreedsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemBreadBinding = DataBindingUtil.bind<ItemBreadBinding>(itemView)
    }
}