package com.moaazelneshawy.catbreed.view.fragment


import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.moaazelneshawy.catbreed.R
import com.moaazelneshawy.catbreed.databinding.FragmentHomeBinding
import com.moaazelneshawy.catbreed.navigators.BreedsNavigator
import com.moaazelneshawy.catbreed.network.models.Breed
import com.moaazelneshawy.catbreed.view.listeners.OnBreedsItemClickListener
import com.moaazelneshawy.catbreed.viewmodel.BreedsViewModel
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(), BreedsNavigator, OnBreedsItemClickListener {

    lateinit var homeBinding: FragmentHomeBinding
    private lateinit var viewModel: BreedsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        homeBinding = FragmentHomeBinding.inflate(layoutInflater)

        viewModel = ViewModelProviders.of(this).get(BreedsViewModel::class.java)
        viewModel.navigator = this
        viewModel.getBreeds(this)
        homeBinding.onBreedClick = this

        return homeBinding.root
    }


    override fun onBreeds(breeds: List<Breed>) {
        if (!breeds.isNullOrEmpty()) {
            homeBinding.breeds = breeds
            performAnimation(homeLoadingPB, homeBreedsRV)
        }

    }

    override fun onLoading(loading: Boolean) {
        homeBinding.isLoading = loading
        if (loading) homeTitleTV.text = getString(R.string.loading_breeds)
        else homeTitleTV.text = getString(R.string.select_breeds)
    }

    override fun onFail(fail: Boolean) {
        if (fail) {
            homeTitleTV.visibility = View.GONE
            activity?.let {
                AlertDialog.Builder(it)
                    .setMessage(R.string.loading_breeds_fail)
                    .setCancelable(false)
                    .setPositiveButton(
                        getString(R.string.retry),
                        DialogInterface.OnClickListener({ dialog: DialogInterface?, which: Int ->
                            dialog?.dismiss()
                            viewModel.getBreeds(this)
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
        } else homeTitleTV.visibility = View.VISIBLE
    }


    override fun onBreedClick(breedId: String) {
        openDetails(breedId)
    }

    private fun openDetails(breedId: String) {
        val bundle = Bundle()
        bundle.putString(getString(R.string.breed), breedId)
        findNavController().navigate(R.id.breedDetailsFragment, bundle)
    }

    private fun performAnimation(viewToHide: View, viewToShow: View) {
        viewToHide.animate().alpha(0.0f)
        viewToShow.animate().alpha(1.0f)
    }


}
