package com.mayandro.cleanarchbaseproject.ui.home.items

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.mayandro.cleanarchbaseproject.R
import com.mayandro.cleanarchbaseproject.databinding.FragmentItemsBinding
import com.mayandro.cleanarchbaseproject.ui.base.BaseFragment
import com.mayandro.cleanarchbaseproject.ui.home.items.adapter.ItemsAdapter
import com.mayandro.cleanarchbaseproject.utility.rotate

class AllItemsFragment : BaseFragment<FragmentItemsBinding, AllItemsFragmentViewModel>(AllItemsFragmentViewModel::class){

    val adapter = ItemsAdapter()

    override fun layoutId(): Int = R.layout.fragment_items

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()

        binding.imageViewPokeball.rotate()

        viewModel.getAllItems().observe(viewLifecycleOwner, Observer {
            (binding.recyclerView.adapter as ItemsAdapter).setData(it)
        })
    }

    private fun setRecyclerView() {
        binding.recyclerView.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerView.adapter = adapter
    }
}