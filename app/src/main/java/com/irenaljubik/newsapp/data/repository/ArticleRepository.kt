package com.irenaljubik.newsapp.data.repository

import com.irenaljubik.newsapp.core.paging.Listing
import com.irenaljubik.newsapp.core.data.Query
import com.irenaljubik.newsapp.core.coroutineproviders.CoroutineProvider
import com.irenaljubik.newsapp.data.api.ArticleQuery
import com.irenaljubik.newsapp.data.api.NewsService
import com.irenaljubik.newsapp.data.models.Article
import javax.inject.Inject

class ArticleRepository @Inject constructor(
    private val newsService: NewsService,
    private val articleListingFactory: ArticleListingFactory,
    private val coroutineProvider: CoroutineProvider
) {
    fun getTopHeadlines(query: ArticleQuery): Listing<Article> {
        val newQuery = query.copy(country = "de")
        val sourceFactory = TopHeadlinesDataSourceFactory(newQuery, newsService, coroutineProvider)
        return articleListingFactory.create(sourceFactory)
    }

    fun getNews(query: ArticleQuery): Listing<Article> {
        val newQuery = query.copy(language = "de")
        val sourceFactory = NewsDataSourceFactory(newQuery, newsService, coroutineProvider)
        return articleListingFactory.create(sourceFactory)
    }
}