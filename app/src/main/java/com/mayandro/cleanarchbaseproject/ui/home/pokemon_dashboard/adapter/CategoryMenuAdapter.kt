package com.mayandro.cleanarchbaseproject.ui.home.pokemon_dashboard.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.mayandro.cleanarchbaseproject.R
import com.mayandro.cleanarchbaseproject.databinding.ItemCategoryMenuBinding


class CategoryMenuAdapter(
    private val dataSet: List<CategoryMenu>
) : RecyclerView.Adapter<CategoryMenuAdapter.ViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(
            R.layout.item_category_menu, viewGroup,
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
        fun onDropDownClick(item: CategoryMenu, position: Int)
    }

    inner class ViewHolder(private val binding: ItemCategoryMenuBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onItemClickListener?.onDropDownClick(dataSet[adapterPosition], adapterPosition)
            }


        }

        fun bind(data: CategoryMenu) {
            binding.cardView.setCardBackgroundColor(ContextCompat.getColor(binding.root.context, data.colorRes))
            binding.textViewTitle.text = data.title
        }
    }
}

data class CategoryMenu(
    val title: String,
    val destinationId: Int,
    val colorRes: Int
)