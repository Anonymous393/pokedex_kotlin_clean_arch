package com.mayandro.cleanarchbaseproject.ui.home.data_setup

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mayandro.cleanarchbaseproject.R
import com.mayandro.cleanarchbaseproject.data.model.apimodel.PokedexItem
import com.mayandro.cleanarchbaseproject.databinding.FragmentDataSetupBinding
import com.mayandro.cleanarchbaseproject.ui.base.BaseFragment
import com.mayandro.cleanarchbaseproject.utility.data_observer.PokedexDataObserver
import com.mayandro.cleanarchbaseproject.utility.rotate
import org.koin.android.ext.android.inject
import java.util.*


class DataSetupFragment : BaseFragment<FragmentDataSetupBinding, DataSetupFragmentViewModel>(DataSetupFragmentViewModel::class), Observer {

    private val pokedexDataObserver: PokedexDataObserver by inject()

    override fun layoutId(): Int = R.layout.fragment_data_setup

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pokedexDataObserver.addObserver(this)

        binding.progressBar.visibility = View.GONE
        binding.textViewProgress.visibility = View.GONE
        binding.textViewProgressValue.visibility = View.GONE
        Glide.with(binding.imageViewPokemon.context)
            .load(resources.getDrawable(R.drawable.pikachu))
            .centerCrop().thumbnail(0.1f)
            .dontAnimate()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .into(binding.imageViewPokemon)

        setUpProgressBar()

        binding.imageViewPokeballTop.rotate()
        binding.imageViewPokeballMiddle.rotate()
    }

    private fun setUpProgressBar() {
        binding.progressBar.max = 100
        binding.progressBar.setPadding(0,0,0,0)
    }

    override fun update(observable: Observable?, p1: Any?) {
        if (observable is PokedexDataObserver && this.isVisible) {
            activity?.runOnUiThread {
                setUpPokemonData(observable.getCurrentPokemon(), observable.getPokedexProgress())
            }

            if(observable.getPokedexFetchFinished()) {
                if (findNavController().currentDestination?.id == R.id.dataSetupFragment) {
                    findNavController().navigate(R.id.action_dataSetupFragment_to_dashboardFragment)
                }
            }
        }
    }

    private fun setUpPokemonData(
        currentPokemon: PokedexItem?,
        progress: Int
    ) {

        currentPokemon?.let {
            binding.textViewPokedexNumber.text = "${it.nDex}"
            binding.textViewPokemon.text = it.name
            Glide.with(binding.imageViewPokemon.context)
                .load(it.image)
                .centerCrop().thumbnail(0.1f)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(binding.imageViewPokemon)
        }
        binding.progressBar.visibility = View.VISIBLE
        binding.textViewProgress.visibility = View.VISIBLE
        binding.textViewProgressValue.visibility = View.VISIBLE

        binding.textViewProgressValue.text = "$progress %"
        binding.progressBar.progress = progress
    }
}