package com.mayandro.cleanarchbaseproject.ui.home.locations

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.mayandro.cleanarchbaseproject.R
import com.mayandro.cleanarchbaseproject.databinding.FragmentLocationsBinding
import com.mayandro.cleanarchbaseproject.ui.base.BaseFragment
import com.mayandro.cleanarchbaseproject.ui.home.locations.adapter.LocationAdapter
import com.mayandro.cleanarchbaseproject.utility.rotate

class AllLocationsFragment : BaseFragment<FragmentLocationsBinding, AllLocationsFragmentViewModel>(AllLocationsFragmentViewModel::class){

    val adapter = LocationAdapter()

    override fun layoutId(): Int = R.layout.fragment_locations

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()

        binding.imageViewPokeball.rotate()

        viewModel.getAllLocations().observe(viewLifecycleOwner, Observer {
            (binding.recyclerView.adapter as LocationAdapter).setData(it)
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