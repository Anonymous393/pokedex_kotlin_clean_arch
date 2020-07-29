package com.mayandro.cleanarchbaseproject.ui.home.moves

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.mayandro.cleanarchbaseproject.R
import com.mayandro.cleanarchbaseproject.databinding.FragmentAllMovesBinding
import com.mayandro.cleanarchbaseproject.ui.base.BaseFragment
import com.mayandro.cleanarchbaseproject.ui.home.moves.adapter.MovesAdapter
import com.mayandro.cleanarchbaseproject.utility.rotate

class AllMovesFragment : BaseFragment<FragmentAllMovesBinding, AllMovesFragmentViewModel>(AllMovesFragmentViewModel::class){

    val adapter = MovesAdapter()

    override fun layoutId(): Int = R.layout.fragment_all_moves

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()

        binding.imageViewPokeball.rotate()

        viewModel.getAllMoves().observe(viewLifecycleOwner, Observer {
            (binding.recyclerView.adapter as MovesAdapter).setData(it)
        })
    }

    private fun setRecyclerView() {
        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }
}