package com.irenaljubik.newsapp.core.data


interface Query {
    fun toQuery(): Map<String, String>
}