package com.mayandro.cleanarchbaseproject.ui.home.pokedex_item

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayoutMediator
import com.mayandro.cleanarchbaseproject.R
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.Pokemon
import com.mayandro.cleanarchbaseproject.databinding.FragmentPokedexItemBinding
import com.mayandro.cleanarchbaseproject.ui.base.BaseFragment
import com.mayandro.cleanarchbaseproject.ui.home.pokedex_item.adapter.PokedexPokemonAdapter
import com.mayandro.cleanarchbaseproject.utility.getPokemonTypeBackgroundResId
import com.mayandro.cleanarchbaseproject.utility.getPokemonTypeResId
import com.mayandro.cleanarchbaseproject.utility.rotate
import java.lang.IllegalStateException


class PokedexItemFragment : BaseFragment<FragmentPokedexItemBinding, PokedexItemFragmentViewModel>(PokedexItemFragmentViewModel::class) {

    override fun layoutId(): Int = R.layout.fragment_pokedex_item

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemonId = arguments?.getInt("pokemonId") ?: return
        val pokedexEntry = arguments?.getInt("pokedexEntry") ?: return

        if(savedInstanceState == null) {
            viewModel.getPokemonForId(pokemonId)
            viewModel.fetchPokemonDataFromDB(pokemonId, pokedexEntry)
            viewModel.getPokemonStats(pokemonId)
            viewModel.getPokemonMovesForId(pokemonId)
        }

        binding.imageViewBack.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.mutablePokemonResponseData.observe(viewLifecycleOwner, Observer {
            it?.let {
                setUpPagerView()
                setPokemonData(it)
                it.types?.let {types ->
                    setCategoryChips(types.filter { it.isNotEmpty() })
                    Glide.with(binding.imageViewPokemon.context)
                        .load(types.first().getPokemonTypeBackgroundResId())
                        .centerCrop()
                        .into(binding.imageView)
                }
            }
        })

        viewModel.mutablePokemonDataResponseData.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding.textViewPokemonSpecies.text = "${it.species}"
            }
        })
    }

    private fun setPokemonData(pokemonResponse: Pokemon) {
        binding.textViewPokemonName.text = if(pokemonResponse.name.contains("("))
            pokemonResponse.name.substring(pokemonResponse.name.indexOf("(") + 1, pokemonResponse.name.indexOf(")"))
        else
            pokemonResponse.name
        binding.textViewPokemonNumber.text = "#${pokemonResponse.number}"
        pokemonResponse.sprite?.let {
            Glide.with(binding.imageViewPokemon.context)
                .load(it)
                .centerCrop()
                .into(binding.imageViewPokemon)
        }
    }

    private fun setCategoryChips(list: List<String>) {
        for (type in list) {
            val mChip = this.layoutInflater.inflate(R.layout.item_chip_category, null, false) as Chip
            mChip.text = type
            mChip.chipIcon = ContextCompat.getDrawable(requireContext(), type.getPokemonTypeResId())
            val paddingDp = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 10f,
                resources.displayMetrics
            ).toInt()
            mChip.setPadding(paddingDp, 0, paddingDp, 0)
            mChip.setOnCheckedChangeListener { compoundButton, b -> }
            binding.chipGroupPokemonType.addView(mChip)
        }
    }

    private fun setUpPagerView() {
        binding.viewPager.adapter =
            PokedexPokemonAdapter(
                childFragmentManager,
                lifecycle
            )
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val title = when(position) {
                0 -> "About"
                1 -> "Stats"
                2 -> "Moves"
                else -> "Unknown"
            }
            tab.text = title
        }.attach()
    }
}