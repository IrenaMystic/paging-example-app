package com.irenaljubik.newsapp.core.utils

import android.widget.ImageView
import coil.load
import com.irenaljubik.newsapp.R

fun ImageView.loadImage(path: String?) {
    this.load(path) {
        error(R.drawable.image_placeholder_error)
        placeholder(R.drawable.image_placeholder)
    }
}