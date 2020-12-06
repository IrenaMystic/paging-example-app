package com.irenaljubik.newsapp.data.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface NewsService {

    @GET("top-headlines")
    fun getTopHeadlinesCall(
        @QueryMap query: Map<String, String>,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Call<ArticleResponseBody>

    @GET("everything")
    fun getNewsCall(
        @QueryMap query: Map<String, String>,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Call<ArticleResponseBody>

}