package com.mayandro.cleanarchbaseproject.ui.home.pokemon_dashboard.adapter

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mayandro.cleanarchbaseproject.R
import com.mayandro.cleanarchbaseproject.data.model.PokemonNews
import com.mayandro.cleanarchbaseproject.databinding.ItemPokemonNewsBinding


class PokemonNewsAdapter() : RecyclerView.Adapter<PokemonNewsAdapter.ViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null

    private var dataSet: MutableList<PokemonNews> = mutableListOf()

    fun setData(newData: List<PokemonNews>) {
        this.dataSet.addAll(newData)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.item_pokemon_news, viewGroup,
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
        fun onDropDownClick(item: PokemonNews, position: Int)
    }

    inner class ViewHolder(private val binding: ItemPokemonNewsBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClickListener?.onDropDownClick(dataSet[adapterPosition], adapterPosition)
            }
        }

        fun bind(data: PokemonNews) {
            binding.textViewTitle.text = data.title
            binding.textViewDate.text = data.description
            Glide.with(binding.imageViewNews.context)
                .load(data.image)
                .thumbnail(0.1f)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .centerCrop()
                .into(binding.imageViewNews)
        }
    }
}

class ItemDecorationColumns(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildLayoutPosition(view)
        val manager = parent.layoutManager as GridLayoutManager?
        val spanCount = manager?.spanCount ?: 0

        if (position < spanCount) {
            outRect.top = space
        }

        if (position % 2 != 0) {
            outRect.right = space
        }
        outRect.left = space
        outRect.bottom = space
    }
}