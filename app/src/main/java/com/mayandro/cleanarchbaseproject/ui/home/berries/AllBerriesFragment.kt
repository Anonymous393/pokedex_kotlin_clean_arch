package com.mayandro.cleanarchbaseproject.ui.home.berries

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.mayandro.cleanarchbaseproject.R
import com.mayandro.cleanarchbaseproject.databinding.FragmentBerriesBinding
import com.mayandro.cleanarchbaseproject.ui.base.BaseFragment
import com.mayandro.cleanarchbaseproject.ui.home.berries.adapter.BerriesAdapter
import com.mayandro.cleanarchbaseproject.utility.rotate

class AllBerriesFragment : BaseFragment<FragmentBerriesBinding, AllBerriesFragmentViewModel>(AllBerriesFragmentViewModel::class){

    val adapter = BerriesAdapter()

    override fun layoutId(): Int = R.layout.fragment_berries

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()

        binding.imageViewPokeball.rotate()

        viewModel.getAllBerries().observe(viewLifecycleOwner, Observer {
            (binding.recyclerView.adapter as BerriesAdapter).setData(it)
        })
    }

    private fun setRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerView.adapter = adapter
    }
}