package com.irenaljubik.newsapp.data.api

import com.irenaljubik.newsapp.di.qualifiers.ApiKey
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(@ApiKey val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val builder = request.newBuilder()
        builder.header("Authorization", String.format("Bearer %s", apiKey))
        request = builder.build()
        return chain.proceed(request)
    }

}