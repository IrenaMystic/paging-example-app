package com.irenaljubik.newsapp.core.ui

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

open class DataBoundViewHolder<T : ViewDataBinding>(val binding: T) :
    RecyclerView.ViewHolder(binding.root)