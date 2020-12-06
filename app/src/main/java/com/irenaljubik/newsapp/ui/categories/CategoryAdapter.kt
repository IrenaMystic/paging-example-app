package com.irenaljubik.newsapp.ui.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.irenaljubik.newsapp.core.ui.DataBoundListAdapter
import com.irenaljubik.newsapp.core.ui.OnItemClickListener
import com.irenaljubik.newsapp.databinding.ItemCategoryBinding

class CategoryAdapter(private val onItemClickListener: OnItemClickListener<String>) :
    DataBoundListAdapter<String, ItemCategoryBinding>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): ItemCategoryBinding {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        binding.root.setOnClickListener {
            binding.category?.run { onItemClickListener.onItemClick(this) }

        }
        return binding
    }

    override fun bind(binding: ItemCategoryBinding, item: String?, position: Int) {
        binding.takeIf { !item.isNullOrEmpty() }?.apply {
            category = item
            executePendingBindings()
        }
    }
}
