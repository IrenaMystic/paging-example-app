package com.irenaljubik.newsapp.core.adapters

import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import coil.load
import com.irenaljubik.newsapp.R
import com.irenaljubik.newsapp.core.utils.loadImage

class BindingAdapters {

    @BindingAdapter("visibleGone")
    fun View.setVisibleGone(value: Boolean) {
        isVisible = value
    }

    @BindingAdapter("backgroundImagePath")
    fun AppCompatImageView.setImagePathWithGlide(path: String?) {
        path?.let { this.loadImage(path) } ?: this.setImageResource(R.drawable.image_placeholder_error)
    }
}