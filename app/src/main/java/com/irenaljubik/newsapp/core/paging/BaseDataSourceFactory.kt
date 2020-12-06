package com.irenaljubik.newsapp.core.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

abstract class BaseDataSourceFactory<T, V>(
    val sourceLiveData: MutableLiveData<BasePagedDataSource<T, V>> = MutableLiveData<BasePagedDataSource<T, V>>(),
) : DataSource.Factory<T, V>()