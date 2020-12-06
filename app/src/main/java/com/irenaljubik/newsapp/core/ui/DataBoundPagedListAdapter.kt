package com.irenaljubik.newsapp.core.ui

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

abstract class DataBoundPagedListAdapter<T, V : ViewDataBinding>(diffCallback: DiffUtil.ItemCallback<T>) :
    PagedListAdapter<T, DataBoundViewHolder<V>>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBoundViewHolder<V> {
        val binding = createBinding(parent, viewType)
        return DataBoundViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataBoundViewHolder<V>, position: Int) {
        bind(holder.binding, getItem(position), position)
        holder.binding.executePendingBindings()
    }

    protected abstract fun createBinding(parent: ViewGroup, viewType: Int): V
    protected abstract fun bind(binding: V, item: T?, position: Int)
}