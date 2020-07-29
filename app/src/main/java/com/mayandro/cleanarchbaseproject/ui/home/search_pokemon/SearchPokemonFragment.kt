package com.mayandro.cleanarchbaseproject.ui.home.search_pokemon

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mayandro.cleanarchbaseproject.R
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.Pokemon
import com.mayandro.cleanarchbaseproject.databinding.FragmentSearchPokemonBinding
import com.mayandro.cleanarchbaseproject.ui.base.BaseFragment
import com.mayandro.cleanarchbaseproject.ui.home.search_pokemon.adapter.SearchResultAdapter
import com.mayandro.cleanarchbaseproject.utility.rotate


class SearchPokemonFragment : BaseFragment<FragmentSearchPokemonBinding, SearchPokemonFragmentViewModel>(SearchPokemonFragmentViewModel::class) {

    override fun layoutId(): Int = R.layout.fragment_search_pokemon

    val searchResultAdapter =
        SearchResultAdapter()

    private val onQueryTextListener: TextWatcher =
        object : TextWatcher {
            private fun getPokemomFromDb(searchText: String) {
                if(searchText.isEmpty()) {
                    searchResultAdapter.setData(emptyList())
                    return
                }

                viewModel.getAllPokemon(searchText).observe(viewLifecycleOwner, Observer {
                    searchResultAdapter.setData(it)
                })
            }

            override fun afterTextChanged(p0: Editable?) {
                getPokemomFromDb(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                getPokemomFromDb(p0.toString())
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpSearchResultRecyclerView()

        binding.editTextSearch.requestFocus()
        showKeyboard()
        binding.editTextSearch.addTextChangedListener (onQueryTextListener)

        binding.imageViewPokeballBottom.rotate()
    }

    private fun setUpSearchResultRecyclerView() {
        searchResultAdapter.setOnItemClickListener(object : SearchResultAdapter.OnItemClickListener {
            override fun onDropDownClick(item: Pokemon, position: Int) {
                hideKeyboard()
                val bundle = bundleOf("pokedexEntry" to item.number, "pokemonId" to item.orderID)
                findNavController().navigate(R.id.action_searchPokemonFragment_to_pokedexItemFragment, bundle)
            }
        })
        binding.recyclerViewSearchResult.setHasFixedSize(true)
        binding.recyclerViewSearchResult.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewSearchResult.adapter = searchResultAdapter
    }

    private fun showKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.showSoftInput(binding.editTextSearch, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun hideKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.hideSoftInputFromWindow(binding.editTextSearch.windowToken, 0)
    }
}

