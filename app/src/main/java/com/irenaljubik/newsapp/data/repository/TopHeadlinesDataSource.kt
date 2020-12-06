package com.irenaljubik.newsapp.data.repository

import com.irenaljubik.newsapp.core.coroutineproviders.CoroutineProvider
import com.irenaljubik.newsapp.core.data.Query
import com.irenaljubik.newsapp.data.api.ArticleResponseBody
import com.irenaljubik.newsapp.data.api.NewsService
import retrofit2.Response

class TopHeadlinesDataSource(
    private val newsService: NewsService,
    query: Query,
    cp: CoroutineProvider
) : ArticleDataSource(query, cp) {

    override fun executeRequest(
        query: Query,
        page: Int,
        pageSize: Int
    ): Response<ArticleResponseBody> {
        return newsService.getTopHeadlinesCall(query.toQuery(), page, pageSize).execute()
    }

}