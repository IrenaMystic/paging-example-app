package com.irenaljubik.newsapp.data.repository

import androidx.paging.DataSource
import com.irenaljubik.newsapp.core.data.Query
import com.irenaljubik.newsapp.core.coroutineproviders.CoroutineProvider
import com.irenaljubik.newsapp.core.paging.BaseDataSourceFactory
import com.irenaljubik.newsapp.data.api.NewsService
import com.irenaljubik.newsapp.data.models.Article

class NewsDataSourceFactory(
    private val query: Query,
    private val newsService: NewsService,
    private val coroutineProvider: CoroutineProvider
) : BaseDataSourceFactory<Int, Article>() {

    override fun create(): DataSource<Int, Article> {
        val latestSource = NewsDataSource(newsService, query, coroutineProvider)
        sourceLiveData.postValue(latestSource)
        return latestSource
    }
}