package com.mayandro.cleanarchbaseproject.ui.home.types

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.mayandro.cleanarchbaseproject.R
import com.mayandro.cleanarchbaseproject.databinding.FragmentTypesBinding
import com.mayandro.cleanarchbaseproject.ui.base.BaseFragment
import com.mayandro.cleanarchbaseproject.ui.home.types.adapter.TypeAdapter
import com.mayandro.cleanarchbaseproject.utility.rotate

class AllTypesFragment : BaseFragment<FragmentTypesBinding, AllTypesFragmentViewModel>(AllTypesFragmentViewModel::class){

    val adapter = TypeAdapter()

    override fun layoutId(): Int = R.layout.fragment_types

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()

        binding.imageViewPokeball.rotate()

        viewModel.getAllTypes().observe(viewLifecycleOwner, Observer {
            (binding.recyclerView.adapter as TypeAdapter).setData(it)
        })
    }

    private fun setRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerView.adapter = adapter
    }
}