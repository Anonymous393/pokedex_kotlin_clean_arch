package com.mayandro.cleanarchbaseproject.ui.home.types.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.mayandro.cleanarchbaseproject.R
import com.mayandro.cleanarchbaseproject.data.model.apimodel.PokemonTypesResponseItem
import com.mayandro.cleanarchbaseproject.databinding.ItemPokemonTypesBinding
import com.mayandro.cleanarchbaseproject.utility.getPokemonTypeResId
import java.lang.Exception

class TypeAdapter() : RecyclerView.Adapter<TypeAdapter.ViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null

    private var dataSet: MutableList<PokemonTypesResponseItem> = mutableListOf()

    fun setData(newData: List<PokemonTypesResponseItem>) {
        this.dataSet.clear()
        this.dataSet.addAll(newData)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.item_pokemon_types, viewGroup,
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
        fun onItemClick(item: PokemonTypesResponseItem, position: Int)
    }

    inner class ViewHolder(private val binding: ItemPokemonTypesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClickListener?.onItemClick(dataSet[adapterPosition], adapterPosition)
            }
        }

        fun bind(data: PokemonTypesResponseItem) {
            try{
                Glide.with(binding.imageView.context)
                    .asBitmap()
                    .load(data.name.getPokemonTypeResId())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .dontAnimate()
                    .placeholder(R.drawable.placeholder)
                    .listener(object : RequestListener<Bitmap> {
                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            return false
                        }

                        override fun onResourceReady(
                            resource: Bitmap?,
                            model: Any?,
                            target: Target<Bitmap>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            if (resource != null) {
                                val palette = Palette.from(resource).generate()
                                // Use generated instance
                                val color = palette.getDarkVibrantColor(
                                    ContextCompat.getColor(
                                        binding.imageView.context,
                                        R.color.background_white
                                    )
                                )
                                //binding.cardView.setCardBackgroundColor(color)
                            }
                            return false
                        }

                    })
                    .into(binding.imageView)
            } catch (e: Exception) {
                binding.cardView.visibility = View.GONE
            }

        }
    }
}