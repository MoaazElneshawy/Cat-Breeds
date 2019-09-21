package com.moaazelneshawy.catbreed.view.fragment


import android.content.DialogInterface
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProviders

import com.moaazelneshawy.catbreed.R
import com.moaazelneshawy.catbreed.databinding.FragmentBreedDetailsBinding
import com.moaazelneshawy.catbreed.navigators.BreedDetailsNavigator
import com.moaazelneshawy.catbreed.network.models.Breed
import com.moaazelneshawy.catbreed.view.listeners.OnWikiUrlClickListener
import com.moaazelneshawy.catbreed.viewmodel.BreedDetailsViewModel
import kotlinx.android.synthetic.main.fragment_breed_details.*
import androidx.navigation.fragment.findNavController
import com.moaazelneshawy.catbreed.view.listeners.OnBackClicked
import com.moaazelneshawy.catbreed.view.listeners.OnLoadMoreImages


/**
 * A simple [Fragment] subclass.
 */
class BreedDetailsFragment : Fragment(), BreedDetailsNavigator,
    OnWikiUrlClickListener, OnLoadMoreImages, OnBackClicked {


    private val TAG = BreedDetailsFragment::class.java.simpleName
    private lateinit var viewModel: BreedDetailsViewModel
    private lateinit var detailsBinding: FragmentBreedDetailsBinding
    lateinit var id: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProviders.of(this).get(BreedDetailsViewModel::class.java)

        detailsBinding = FragmentBreedDetailsBinding.inflate(inflater)
        detailsBinding.onWikiClick = this
        detailsBinding.loadMoreImages = this
        detailsBinding.onBackClicked = this
        return detailsBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        id = arguments?.getString(getString(R.string.breed)).toString()
        viewModel.getImage(this, id)
        viewModel.navigator = this

        detailsFragmentWikiTV.setPaintFlags(Paint.UNDERLINE_TEXT_FLAG)

        detailsFragmentLoadingPB.getIndeterminateDrawable().setColorFilter(
            resources.getColor(R.color.colorGolden),
            android.graphics.PorterDuff.Mode.MULTIPLY
        )

    }


    override fun onWikiClick(url: String) {
        val wikiIntent = Intent(android.content.Intent.ACTION_VIEW)
        wikiIntent.data = Uri.parse(url)
        startActivity(wikiIntent)
    }


    override fun onLoadImage(url: String?) {
        detailsBinding.url = url
    }

    override fun onLoadBreed(breed: Breed) {
        detailsBinding.breed = breed
        performAnimation(
            detailsFragmentLoadingPB,
            detailsFragmentDetailsContainer
        )
    }

    override fun onLoading(loading: Boolean) {
        detailsBinding.isLoading = loading
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
                            viewModel.getImage(this, id)
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

    private fun performAnimation(viewToHide: View, viewToShow: View) {
        viewToHide.animate().alpha(0.0f)
        viewToShow.animate().alpha(1.0f)
    }

    override fun loadMoreImages(breed: Breed) {
        val bundle = Bundle()
        bundle.putSerializable(getString(R.string.breed), breed)
        findNavController().navigate(R.id.moreImagesFragment, bundle)
    }

    override fun onBack() {
        findNavController().navigateUp()
        findNavController().popBackStack()
    }
}
