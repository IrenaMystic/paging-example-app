package com.irenaljubik.newsapp.di.modules

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.irenaljubik.newsapp.data.api.AuthInterceptor
import com.irenaljubik.newsapp.di.qualifiers.ApiUrl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGsonBuilder(): GsonBuilder = GsonBuilder()


    @Singleton
    @Provides
    fun provideOkHttpClient(authInterceptor: AuthInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gson: Gson,
        okHttpClient: OkHttpClient,
        @ApiUrl apiUrl: String
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
    }

}