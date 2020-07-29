package com.mayandro.cleanarchbaseproject.ui.home.pokedex.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mayandro.cleanarchbaseproject.R
import com.mayandro.cleanarchbaseproject.data.source.local.room.entity.Pokemon
import com.mayandro.cleanarchbaseproject.databinding.ItemPokemonImageBinding

class PokemonImageAdapter() : RecyclerView.Adapter<PokemonImageAdapter.ViewHolder>() {
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
            R.layout.item_pokemon_image, viewGroup,
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
        fun onItemClick(item: Pokemon, position: Int)
    }

    inner class ViewHolder(private val binding: ItemPokemonImageBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClickListener?.onItemClick(dataSet[adapterPosition], adapterPosition)
            }
        }

        fun bind(data: Pokemon) {
            data.sprite?.let {
                Glide.with(binding.imageViewPokemon.context)
                    .load(it)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .dontAnimate()
                    .placeholder(R.drawable.placeholder)
                    .into(binding.imageViewPokemon)
            }
        }
    }
}