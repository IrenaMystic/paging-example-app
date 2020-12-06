package com.irenaljubik.newsapp.data.repository

import com.irenaljubik.newsapp.core.coroutineproviders.CoroutineProvider
import com.irenaljubik.newsapp.core.data.NetworkState
import com.irenaljubik.newsapp.core.data.Query
import com.irenaljubik.newsapp.core.paging.BasePagedDataSource
import com.irenaljubik.newsapp.data.api.ArticleResponseBody
import com.irenaljubik.newsapp.data.models.Article
import org.json.JSONObject
import retrofit2.Response
import kotlin.math.ceil

abstract class ArticleDataSource(
    private val query: Query,
    cp: CoroutineProvider
) : BasePagedDataSource<Int, Article>(cp) {

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Article>
    ) {
        loadPage(1,
            params.requestedLoadSize,
            { loadInitial(params, callback) },
            { data, nextPage ->
                callback.onResult(data, null, nextPage)
            })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
        loadPage(
            params.key,
            params.requestedLoadSize,
            { loadAfter(params, callback) },
            { data, nextPage ->
                callback.onResult(data, nextPage)
            })
    }

    private fun loadPage(
        page: Int,
        pageSize: Int,
        retryCallback: () -> Unit,
        successCallback: (List<Article>, Int?) -> Unit
    ) {
        updateState(NetworkState.LOADING)

        try {
            val response = executeRequest(query, page, pageSize)

            if (response.isSuccessful) {
                val data = response.body()?.articles ?: emptyList()
                val totalResults = response.body()?.totalResults
                val nextPage = page + 1
                val totalPages = ceil((totalResults?.div(pageSize.toFloat())) ?: 0f).toInt()

                retry = null
                updateState(NetworkState.LOADED)

                successCallback(data, if (nextPage <= totalPages) nextPage else null)
            } else {
                retry = retryCallback
                try {
                    val jsonObj = JSONObject(response.errorBody()?.string() ?: "")
                    onError(jsonObj.getString("message"))
                } catch (exception: Exception) {
                    onError("API Error")
                }
            }
        } catch (exception: Exception) {
            retry = retryCallback
            onError(exception)
        }
    }

    abstract fun executeRequest(
        query: Query,
        page: Int,
        pageSize: Int
    ): Response<ArticleResponseBody>

}