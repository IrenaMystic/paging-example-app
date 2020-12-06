package com.irenaljubik.newsapp.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.irenaljubik.newsapp.BuildConfig
import com.irenaljubik.newsapp.data.api.NewsService
import com.irenaljubik.newsapp.data.repository.ArticleListingFactory
import com.irenaljubik.newsapp.di.qualifiers.ApiKey
import com.irenaljubik.newsapp.di.qualifiers.ApiUrl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class AppModule {

    @Singleton
    @Provides
    @ApiUrl
    fun provideApiUrl(): String = BuildConfig.API_URL

    @Singleton
    @Provides
    @ApiKey
    fun provideApiKey(): String = BuildConfig.API_KEY

    @Singleton
    @Provides
    fun provideGson(gsonBuilder: GsonBuilder): Gson = gsonBuilder.create()

    @Singleton
    @Provides
    fun provideNewsService(retrofit: Retrofit): NewsService {
        return retrofit.create(NewsService::class.java)
    }

    @Singleton
    @Provides
    fun providesArticleListingFactory() = ArticleListingFactory()
    
}