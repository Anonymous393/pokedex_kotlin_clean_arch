package com.mayandro.cleanarchbaseproject.ui.home.pokemon_dashboard

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mayandro.cleanarchbaseproject.R
import com.mayandro.cleanarchbaseproject.data.model.PokemonNews
import com.mayandro.cleanarchbaseproject.databinding.FragmentPokemonDashboardBinding
import com.mayandro.cleanarchbaseproject.ui.base.BaseFragment
import com.mayandro.cleanarchbaseproject.ui.home.pokemon_dashboard.adapter.CategoryMenu
import com.mayandro.cleanarchbaseproject.ui.home.pokemon_dashboard.adapter.CategoryMenuAdapter
import com.mayandro.cleanarchbaseproject.ui.home.pokemon_dashboard.adapter.PokemonNewsAdapter
import com.mayandro.cleanarchbaseproject.utility.rotate
import kotlinx.coroutines.ExperimentalCoroutinesApi

class DashboardFragment : BaseFragment<FragmentPokemonDashboardBinding, DashboardFragmentViewModel>(DashboardFragmentViewModel::class), DashboardFragmentViewInteractor {
    override fun layoutId(): Int = R.layout.fragment_pokemon_dashboard

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewInteractor = this

        setUpMenuRecyclerView()
        setUpNewsRecyclerView()

        binding.imageViewPokeball.rotate()

        binding.searchView.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_searchPokemonFragment)
        }
    }


    private fun setUpMenuRecyclerView() {
        val adapter = CategoryMenuAdapter(listOf(
            CategoryMenu("Pokedex", R.id.action_dashboardFragment_to_pokedexFragment, R.color.background_green),
            CategoryMenu("Moves", R.id.action_dashboardFragment_to_allMovesFragment, R.color.background_red),
            CategoryMenu("Berries", R.id.action_dashboardFragment_to_allBerriesFragment, R.color.background_blue),
            CategoryMenu("Items", R.id.action_dashboardFragment_to_allItemsFragment, R.color.background_yellow),
            CategoryMenu("Locations", R.id.action_dashboardFragment_to_allLocationsFragment, R.color.background_purple),
            CategoryMenu("Type Charts", R.id.action_dashboardFragment_to_allTypesFragment, R.color.background_brown)
        ))
        adapter.setOnItemClickListener(object : CategoryMenuAdapter.OnItemClickListener {
            override fun onDropDownClick(item: CategoryMenu, position: Int) {
                findNavController().navigate(item.destinationId)
            }
        })
        binding.recyclerViewMenu.setHasFixedSize(true)
        binding.recyclerViewMenu.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerViewMenu.adapter = adapter
    }

    private fun setUpNewsRecyclerView() {
        val adapter = PokemonNewsAdapter()
        adapter.setOnItemClickListener(object : PokemonNewsAdapter.OnItemClickListener {
            override fun onDropDownClick(item: PokemonNews, position: Int) {
            }
        })
        binding.recyclerViewNews.setHasFixedSize(true)
        binding.recyclerViewNews.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewNews.adapter = adapter

        val mDividerItemDecoration = DividerItemDecoration(binding.recyclerViewNews.context, DividerItemDecoration.VERTICAL)
        mDividerItemDecoration.setDrawable(ContextCompat.getDrawable(binding.recyclerViewNews.context, R.drawable.divider_medium)!!)
        binding.recyclerViewNews.addItemDecoration(mDividerItemDecoration)

        val list = listOf(
            PokemonNews("New pokemons are available on the pokedex", "12th June 2020", R.drawable.news_1),
            PokemonNews("Learn new moves for your magicarp", "13th June 2020", R.drawable.news_2),
            PokemonNews("Battle your fiends with pokemon", "9th June 2020", R.drawable.news_3),
            PokemonNews("Great news! Jhoto league is online now", "7th June 2020", R.drawable.news_1),
            PokemonNews("New shiny pokemons are available now", "5th June 2020", R.drawable.news_2),
            PokemonNews("New safari zone has been selected for endangered species", "2th June 2020", R.drawable.news_3)
        )
        adapter.setData(list)
    }

    override fun showAlertDialog(title: String, message: String) {

    }
}