package com.moaazelneshawy.catbreed.view.fragment


import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.moaazelneshawy.catbreed.R
import com.moaazelneshawy.catbreed.databinding.FragmentMoreImagesBinding
import com.moaazelneshawy.catbreed.navigators.BreedDetailsNavigator
import com.moaazelneshawy.catbreed.navigators.BreedsImagesNavigator
import com.moaazelneshawy.catbreed.network.models.Breed
import com.moaazelneshawy.catbreed.view.listeners.OnBackClicked
import com.moaazelneshawy.catbreed.viewmodel.BreedDetailsViewModel
import com.moaazelneshawy.catbreed.viewmodel.BreedsImagesViewModel

class MoreImagesFragment : Fragment(), BreedsImagesNavigator, OnBackClicked {


    private val TAG = MoreImagesFragment::class.java.simpleName
    lateinit var moreBinding: FragmentMoreImagesBinding
    lateinit var viewModel: BreedsImagesViewModel
    lateinit var breed: Breed
    var limit: Int = 5

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        moreBinding = FragmentMoreImagesBinding.inflate(layoutInflater)

        viewModel = ViewModelProviders.of(this).get(BreedsImagesViewModel::class.java)

        return moreBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigator = this

        breed = arguments?.getSerializable(getString(R.string.breed)) as Breed
        moreBinding.breed = breed

        viewModel.getImages(this, breed.id, limit)
        viewModel.navigator = this
        moreBinding.onBackClicked = this

    }

    override fun onImagesLoad(images: List<String>) {
        moreBinding.images = images
    }

    override fun onLoading(loading: Boolean) {
        moreBinding.isLoading = loading
    }

    override fun onFail(fail: Boolean) {
        if (fail) {
            this.context?.let {
                AlertDialog.Builder(it)
                    .setMessage(R.string.loading_breeds_fail)
                    .setCancelable(false)
                    .setPositiveButton(
                        getString(R.string.retry),
                        DialogInterface.OnClickListener({ dialog: DialogInterface?, which: Int ->
                            dialog?.dismiss()
                            viewModel.getImages(this, breed.id, limit)
                        })
                    )
                    .setNegativeButton(
                        getString(R.string.close),
                        DialogInterface.OnClickListener({ dialog: DialogInterface?, which: Int ->
                            dialog?.dismiss()
                        })
                    )
                    .show()
            }
        }
    }

    override fun onBack() {
        findNavController().navigateUp()
        findNavController().popBackStack(R.id.moreImagesFragment, true)
    }
}
