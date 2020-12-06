package com.irenaljubik.newsapp.ui.common

import androidx.appcompat.app.AppCompatActivity
import dagger.android.support.DaggerFragment

open class BaseFragment : DaggerFragment() {

    open fun setupToolBarView(
        title: String,
        displayHomeAsUpEnabled: Boolean = true
    ) {
        (requireActivity() as AppCompatActivity).apply {
            this.title = title
            supportActionBar?.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled)
        }
    }

}