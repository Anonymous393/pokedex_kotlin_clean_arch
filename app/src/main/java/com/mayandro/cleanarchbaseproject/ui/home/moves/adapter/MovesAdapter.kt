package com.mayandro.cleanarchbaseproject.ui.home.moves.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mayandro.cleanarchbaseproject.R
import com.mayandro.cleanarchbaseproject.data.model.apimodel.PokemonMovesResponseItem
import com.mayandro.cleanarchbaseproject.databinding.ItemPokemonMovesBinding

class MovesAdapter() : RecyclerView.Adapter<MovesAdapter.ViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null

    private var dataSet: MutableList<PokemonMovesResponseItem> = mutableListOf()

    fun setData(newData: List<PokemonMovesResponseItem>) {
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
        fun onItemClick(item: PokemonMovesResponseItem, position: Int)
    }

    inner class ViewHolder(private val binding: ItemPokemonMovesBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClickListener?.onItemClick(dataSet[adapterPosition], adapterPosition)
            }
        }

        fun bind(data: PokemonMovesResponseItem) {
            binding.chip.text = data.name.capitalize()
        }
    }
}