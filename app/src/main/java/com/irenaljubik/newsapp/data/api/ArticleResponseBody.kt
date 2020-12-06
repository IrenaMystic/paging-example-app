package com.irenaljubik.newsapp.data.api

import com.google.gson.annotations.SerializedName
import com.irenaljubik.newsapp.data.models.Article

data class ArticleResponseBody(
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,
    @SerializedName("articles") val articles: List<Article>
)