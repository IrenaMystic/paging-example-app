package com.irenaljubik.newsapp.ui.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.paging.PagedList
import com.irenaljubik.newsapp.R
import com.irenaljubik.newsapp.core.data.NetworkState
import com.irenaljubik.newsapp.core.data.Status
import com.irenaljubik.newsapp.core.ui.CombinedLiveData
import com.irenaljubik.newsapp.data.api.ArticleQuery
import com.irenaljubik.newsapp.data.models.Article
import com.irenaljubik.newsapp.data.repository.ArticleRepository
import com.irenaljubik.newsapp.ui.common.QueryViewModel
import javax.inject.Inject

class ArticlesViewModel @Inject constructor(
    private val newsRepository: ArticleRepository
) : QueryViewModel<ArticleQuery>() {

    private val repoResult = currentQuery.map { input ->
        if (input.category.isNullOrEmpty()) {
            newsRepository.getNews(input)
        } else {
            newsRepository.getTopHeadlines(input)
        }
    }
    val articles = repoResult.switchMap { input -> input.pagedList }
    val networkState = repoResult.switchMap { input -> input.networkState }
    val showSearchState = MutableLiveData(true)

    val loadingState: LiveData<Boolean> = networkState.map { it.status == Status.LOADING }
    val errorState: LiveData<Boolean> = networkState.map { it.status == Status.FAILED }

    val showLoadingState = CombinedLiveData(
        networkState,
        articles
    ) { state: NetworkState?, data: PagedList<Article>? -> combineLoadingState(state, data) }

    val showEmptyState = CombinedLiveData(
        networkState,
        articles
    ) { state: NetworkState?, list: PagedList<Article>? -> combineEmptyState(state, list) }

    val emptyMessage = MutableLiveData(R.string.no_articles)

    fun refresh() {
        repoResult.value?.refresh?.invoke()
    }

    fun setCategoryQuery(newCategory: String) {
        showSearchState.postValue(false)
        emptyMessage.postValue(R.string.no_category_articles)
        val query = ArticleQuery(category = newCategory)
        nextQuery.value = query
        submitNextQuery()
    }

    fun submitSearch(search: String) {
        if (search.isNotEmpty()) {
            showSearchState.postValue(false)
            emptyMessage.postValue(R.string.no_articles)
            val query = ArticleQuery(search = search)
            nextQuery.value = query
            submitNextQuery()
        }
    }

    private fun combineEmptyState(networkState: NetworkState?, data: PagedList<Article>?): Boolean =
        networkState?.status == Status.SUCCESS && data?.size == 0

    private fun combineLoadingState(
        networkState: NetworkState?,
        data: PagedList<Article>?
    ): Boolean = networkState?.status != Status.SUCCESS && (data == null || data.size == 0)

    override fun getInitialQuery(): ArticleQuery = ArticleQuery()

}