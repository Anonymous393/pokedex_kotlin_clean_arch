package com.mayandro.cleanarchbaseproject.ui.home.pokedex_item.fragments.stats

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mayandro.cleanarchbaseproject.R
import com.mayandro.cleanarchbaseproject.databinding.FragmentStatsBinding
import com.mayandro.cleanarchbaseproject.ui.base.BaseFragment
import com.mayandro.cleanarchbaseproject.ui.home.pokedex_item.PokedexItemFragmentViewModel
import com.mayandro.cleanarchbaseproject.ui.home.pokedex_item.fragments.stats.adapter.PokemonStatsAdapter
import org.koin.androidx.viewmodel.ext.android.getViewModel

class StatsFragment : BaseFragment<FragmentStatsBinding, StatsFragmentViewModel>(StatsFragmentViewModel::class), StatsFragmentViewInteractor {

    private val sharedViewModel by lazy { requireParentFragment().getViewModel<PokedexItemFragmentViewModel>() }

    override fun layoutId(): Int = R.layout.fragment_stats

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        sharedViewModel.statsListLiveData.observe(viewLifecycleOwner, Observer {
            (binding.recyclerViewStats.adapter as PokemonStatsAdapter).setData(it)
        })

    }

    fun setRecyclerView() {
        val pokemonStatsAdapter = PokemonStatsAdapter()
        binding.recyclerViewStats.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewStats.adapter = pokemonStatsAdapter
    }

    override fun showAlertDialog(title: String, message: String) {
        TODO("Not yet implemented")
    }
}