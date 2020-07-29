package com.mayandro.cleanarchbaseproject.ui.home.pokedex_item.fragments.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mayandro.cleanarchbaseproject.R
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.PokemonData
import com.mayandro.cleanarchbaseproject.databinding.FragmentAboutBinding
import com.mayandro.cleanarchbaseproject.ui.base.BaseFragment
import com.mayandro.cleanarchbaseproject.ui.home.pokedex_item.PokedexItemFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class AboutFragment : BaseFragment<FragmentAboutBinding, AboutFragmentViewModel>(AboutFragmentViewModel::class), AboutFragmentViewInteractor {

    private val sharedViewModel by lazy { requireParentFragment().getViewModel<PokedexItemFragmentViewModel>() }

    override fun layoutId(): Int = R.layout.fragment_about

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.mutablePokemonDataResponseData.observe(viewLifecycleOwner, Observer {
            binding.textViewDescription.text = it?.description
            binding.textViewHeightValue.text = "${it.height}"
            binding.textViewWeightValue.text = "${it.weight}"

            if(it.gender.isEmpty()) {

            }

            if(it.gender.size == 1) {
                binding.textViewGenderFeMale.text = "Neutral ${it.gender[0]}"
            }

            if(it.gender.size == 2) {
                binding.textViewGenderFeMale.text = "Female ${it.gender[0]}"
                binding.textViewGenderMale.text = "Male ${it.gender[1]}"
            }

            binding.textViewEggGroupValue.text = it.eggGroups.toString()
        })

        sharedViewModel.mutablePokemonResponseData.observe(viewLifecycleOwner, Observer {
            binding.textViewEggCycleValue.text = it.types.first()
        })
    }
}