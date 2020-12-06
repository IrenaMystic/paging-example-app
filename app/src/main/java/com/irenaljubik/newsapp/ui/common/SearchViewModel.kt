package com.irenaljubik.newsapp.ui.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SearchViewModel @Inject constructor() : ViewModel() {
    val search = MutableLiveData("")
}