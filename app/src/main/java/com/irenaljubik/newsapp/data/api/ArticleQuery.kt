package com.irenaljubik.newsapp.data.api

import com.irenaljubik.newsapp.core.data.Query
import java.util.*

data class ArticleQuery(
    val category: String? = null,
    val search: String? = null,
    val language: String? = null,
    val country: String? = null
) : Query {

    override fun toQuery(): Map<String, String> {
        return hashMapOf<String, String>().apply {
            if (!category.isNullOrEmpty()) {
                put("category", category.toLowerCase(Locale.ROOT))
            }
            if (!search.isNullOrEmpty()) {
                put("q", search.toLowerCase(Locale.ROOT))
            }
            if (!language.isNullOrEmpty()) {
                put("language", language.toLowerCase(Locale.ROOT))
            }
            if (!country.isNullOrEmpty()) {
                put("country", country.toLowerCase(Locale.ROOT))
            }
        }
    }
}