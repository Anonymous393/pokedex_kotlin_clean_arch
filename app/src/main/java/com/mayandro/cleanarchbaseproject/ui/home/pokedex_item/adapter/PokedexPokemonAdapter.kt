package com.mayandro.cleanarchbaseproject.ui.home.pokedex_item.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mayandro.cleanarchbaseproject.ui.home.pokedex_item.fragments.about.AboutFragment
import com.mayandro.cleanarchbaseproject.ui.home.pokedex_item.fragments.moves.MovesFragment
import com.mayandro.cleanarchbaseproject.ui.home.pokedex_item.fragments.stats.StatsFragment


class PokedexPokemonAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AboutFragment()
            1 -> StatsFragment()
            2 -> MovesFragment()
            else -> AboutFragment()
        }
    }

    override fun getItemCount(): Int {
        return 3
    }
}