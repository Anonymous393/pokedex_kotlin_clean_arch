package com.mayandro.cleanarchbaseproject.ui.home.pokedex_item.fragments.stats.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mayandro.cleanarchbaseproject.R
import com.mayandro.cleanarchbaseproject.databinding.ItemPokemonStatsBinding
import com.mayandro.cleanarchbaseproject.ui.home.pokedex_item.PokemonStatsItem


class PokemonStatsAdapter() : RecyclerView.Adapter<PokemonStatsAdapter.ViewHolder>() {
    private var dataSet: MutableList<PokemonStatsItem> = mutableListOf()

    fun setData(newData: List<PokemonStatsItem>) {
        this.dataSet.addAll(newData)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.item_pokemon_stats, viewGroup,
            false
        )
        return ViewHolder(DataBindingUtil.bind(view)!!)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    inner class ViewHolder(private val binding: ItemPokemonStatsBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: PokemonStatsItem) {
            binding.textTitle.text = data.name
            binding.progressBar.progress = data.progress
        }
    }
}