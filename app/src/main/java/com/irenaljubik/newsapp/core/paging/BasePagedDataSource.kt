package com.irenaljubik.newsapp.core.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.irenaljubik.newsapp.core.data.NetworkState
import com.irenaljubik.newsapp.core.coroutineproviders.CoroutineProvider
import kotlinx.coroutines.launch
import java.lang.Exception

abstract class BasePagedDataSource<K, V>(private val coroutineProvider: CoroutineProvider) :
    PageKeyedDataSource<K, V>() {

    private val coroutineScope = coroutineProvider.createCoroutineScope()

    var networkState = MutableLiveData<NetworkState>()
    protected var retry: (() -> Any)? = null

    protected fun updateState(state: NetworkState) {
        this.networkState.postValue(state)
    }

    open fun retryAllFailed() {
        val prevRetry = retry
        retry = null
        prevRetry?.let {
            coroutineScope.launch(coroutineProvider.ioContext()) { it.invoke() }
        }
    }

    protected open fun onError(e: Exception) {
        onError(  e.localizedMessage ?: "Unknown error")
    }

    protected open fun onError(error:String) {
        networkState.postValue(NetworkState.error(error))
    }
}