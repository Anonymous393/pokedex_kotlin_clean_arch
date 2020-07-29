package com.mayandro.cleanarchbaseproject.ui.home.locations.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mayandro.cleanarchbaseproject.R
import com.mayandro.cleanarchbaseproject.data.model.apimodel.PokemonLocationResponseItem
import com.mayandro.cleanarchbaseproject.databinding.ItemPokemonMovesBinding

class LocationAdapter() : RecyclerView.Adapter<LocationAdapter.ViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null

    private var dataSet: MutableList<PokemonLocationResponseItem> = mutableListOf()

    fun setData(newData: List<PokemonLocationResponseItem>) {
        this.dataSet.clear()
        this.dataSet.addAll(newData)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.item_pokemon_moves, viewGroup,
            false
        )
        return ViewHolder(DataBindingUtil.bind(view)!!)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(dataSet[position])
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(item: PokemonLocationResponseItem, position: Int)
    }

    inner class ViewHolder(private val binding: ItemPokemonMovesBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClickListener?.onItemClick(dataSet[adapterPosition], adapterPosition)
            }
        }

        fun bind(data: PokemonLocationResponseItem) {
            binding.chip.text = data.name.capitalize()
        }
    }
}