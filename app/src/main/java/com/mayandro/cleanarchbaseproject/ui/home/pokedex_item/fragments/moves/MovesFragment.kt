package com.mayandro.cleanarchbaseproject.ui.home.pokedex_item.fragments.moves

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.android.material.chip.Chip
import com.mayandro.cleanarchbaseproject.R
import com.mayandro.cleanarchbaseproject.databinding.FragmentMovesBinding
import com.mayandro.cleanarchbaseproject.ui.home.pokedex_item.PokedexItemFragmentViewModel
import com.mayandro.cleanarchbaseproject.utility.getPokemonTypeResId
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MovesFragment : Fragment() {

    private val sharedViewModel by lazy { requireParentFragment().getViewModel<PokedexItemFragmentViewModel>() }

    lateinit var binding: FragmentMovesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_moves,
            container,
            false
        )
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel.movesListLiveData.observe(viewLifecycleOwner, Observer {
            for (type in it) {
                val mChip = this.layoutInflater.inflate(R.layout.item_chip_category, null, false) as Chip
                mChip.text = type
                binding.chipGroupMoves.addView(mChip)
            }
        })

        sharedViewModel.abilityListLiveData.observe(viewLifecycleOwner, Observer {
            for (type in it) {
                val mChip = this.layoutInflater.inflate(R.layout.item_chip_category, null, false) as Chip
                mChip.text = type
                binding.chipGroupAbility.addView(mChip)
            }
        })
    }
}