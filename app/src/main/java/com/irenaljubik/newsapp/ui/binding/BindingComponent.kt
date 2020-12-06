package com.irenaljubik.newsapp.ui.binding

import androidx.databinding.DataBindingComponent
import com.irenaljubik.newsapp.core.adapters.BindingAdapters
import dagger.android.support.DaggerFragment

open class BindingComponent(val fragment: DaggerFragment) : DataBindingComponent {
    override fun getBindingAdapters(): BindingAdapters = BindingAdapters()
}