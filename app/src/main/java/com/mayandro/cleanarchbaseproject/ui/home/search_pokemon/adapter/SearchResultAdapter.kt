package com.mayandro.cleanarchbaseproject.ui.home.search_pokemon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mayandro.cleanarchbaseproject.R
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.Pokemon
import com.mayandro.cleanarchbaseproject.databinding.ItemPokemonSearchBinding


class SearchResultAdapter() : RecyclerView.Adapter<SearchResultAdapter.ViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null

    private var dataSet: MutableList<Pokemon> = mutableListOf()

    fun setData(newData: List<Pokemon>) {
        this.dataSet.clear()
        this.dataSet.addAll(newData)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.item_pokemon_search, viewGroup,
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
        fun onDropDownClick(item: Pokemon, position: Int)
    }

    inner class ViewHolder(private val binding: ItemPokemonSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClickListener?.onDropDownClick(dataSet[adapterPosition], adapterPosition)
            }
        }

        fun bind(data: Pokemon) {
            binding.textViewPokemonName.text = data.name
            Glide.with(binding.imageViewPokemon.context)
                .load(data.sprite)
                .thumbnail(0.1f)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(binding.imageViewPokemon)
        }
    }
}