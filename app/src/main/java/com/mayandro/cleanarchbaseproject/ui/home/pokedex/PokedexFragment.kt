package com.mayandro.cleanarchbaseproject.ui.home.pokedex

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.mayandro.cleanarchbaseproject.R
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.Pokemon
import com.mayandro.cleanarchbaseproject.databinding.FragmentPokedexBinding
import com.mayandro.cleanarchbaseproject.ui.base.BaseFragment
import com.mayandro.cleanarchbaseproject.ui.home.pokedex.adapter.PokemonImageAdapter
import com.mayandro.cleanarchbaseproject.utility.rotate
import kotlinx.coroutines.ExperimentalCoroutinesApi

class PokedexFragment : BaseFragment<FragmentPokedexBinding, PokedexFragmentViewModel>(PokedexFragmentViewModel::class), PokedexFragmentViewInteractor {
    override fun layoutId(): Int = R.layout.fragment_pokedex

    val adapter = PokemonImageAdapter()

    @ExperimentalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewInteractor = this

        setRecyclerView()
        binding.imageViewPokeball.rotate()
        viewModel.listLiveData.observe(viewLifecycleOwner, Observer {
            if(it.isNullOrEmpty()) showAlertDialog("No pokemon", "Empty") else (binding.recyclerView.adapter as PokemonImageAdapter).setData(it)
        })
    }

    private fun setRecyclerView() {
        adapter.setOnItemClickListener(object : PokemonImageAdapter.OnItemClickListener {
            override fun onItemClick(item: Pokemon, position: Int) {
                val bundle = bundleOf("pokedexEntry" to item.number, "pokemonId" to item.orderID)
                findNavController().navigate(R.id.action_dashboardFragment_to_pokedexItemFragment, bundle)
            }
        })
        binding.recyclerView.layoutManager = GridLayoutManager(context, 5)
        binding.recyclerView.adapter = adapter
    }

    override fun showAlertDialog(title: String, message: String) {
        alertDialog.showAlertMessage(
            requireContext(),
            title,
            message,
            "Ok",
            ""
        )
    }

    override fun showLoadingDialog(isLoading: Boolean) {
        handleLoadingDialog(isLoading)
    }
}