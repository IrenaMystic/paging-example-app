package com.irenaljubik.newsapp.core.paging

interface ListingFactory<T, V> {

    fun create(sourceFactory: BaseDataSourceFactory<T, V>): Listing<V>

}