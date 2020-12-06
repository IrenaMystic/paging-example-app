package com.irenaljubik.newsapp.ui.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.irenaljubik.newsapp.core.data.Query

abstract class QueryViewModel<T : Query>: ViewModel() {

    val currentQuery = MutableLiveData<T>()
    val nextQuery = MutableLiveData<T>()

    abstract fun getInitialQuery(): T

    open fun setNextQuery(nextQuery: T) {
        if (nextQuery == this.nextQuery.value) {
            return
        }
        this.nextQuery.value = nextQuery
    }

    open fun submitNextQuery() {
        if (nextQuery.value == currentQuery.value) {
            return
        }
        currentQuery.value = nextQuery.value
    }

    open fun resetQuery() {
        setNextQuery(getInitialQuery())
        submitNextQuery()
    }

}