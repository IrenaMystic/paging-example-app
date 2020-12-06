package com.irenaljubik.newsapp.di.binders

import com.irenaljubik.newsapp.core.coroutineproviders.CoroutineProvider
import com.irenaljubik.newsapp.core.coroutineproviders.DefaultCoroutineProvider
import dagger.Binds
import dagger.Module

@Module
abstract class CoreBinderModule {
    @Binds
    abstract fun bindCoroutineProvider(coroutineProvider: DefaultCoroutineProvider): CoroutineProvider
}