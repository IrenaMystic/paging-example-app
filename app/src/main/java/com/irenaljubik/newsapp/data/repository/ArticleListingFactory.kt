package com.irenaljubik.newsapp.data.repository

import androidx.lifecycle.switchMap
import androidx.paging.Config
import androidx.paging.LivePagedListBuilder
import com.irenaljubik.newsapp.core.paging.BaseDataSourceFactory
import com.irenaljubik.newsapp.core.paging.Listing
import com.irenaljubik.newsapp.core.paging.ListingFactory
import com.irenaljubik.newsapp.data.models.Article
import javax.inject.Inject

class ArticleListingFactory @Inject constructor() : ListingFactory<Int, Article> {

    companion object {
        const val PAGE_SIZE = 10
    }

    override fun create(sourceFactory: BaseDataSourceFactory<Int, Article>): Listing<Article> {
        val config = Config(
            pageSize = PAGE_SIZE,
            initialLoadSizeHint = PAGE_SIZE,
            prefetchDistance = PAGE_SIZE,
            enablePlaceholders = true
        )

        val livePagedList = LivePagedListBuilder(sourceFactory, config).build()
        val networkState = sourceFactory.sourceLiveData.switchMap { input -> input.networkState }
        return Listing(livePagedList,
            networkState,
            {
                sourceFactory.sourceLiveData.value?.invalidate()
            },
            {
                sourceFactory.sourceLiveData.value?.retryAllFailed()
            }
        )
    }
}