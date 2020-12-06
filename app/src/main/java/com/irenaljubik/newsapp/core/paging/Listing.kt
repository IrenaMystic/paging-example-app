package com.irenaljubik.newsapp.core.paging

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.irenaljubik.newsapp.core.data.NetworkState

data class Listing<T>(
    val pagedList: LiveData<PagedList<T>>,
    val networkState: LiveData<NetworkState>,
    val refresh: () -> Unit,
    val retry: () -> Unit
)