package com.irenaljubik.newsapp.di.binders

import androidx.lifecycle.ViewModel
import com.irenaljubik.newsapp.di.factory.ConstructorInjectionViewModelFactory
import com.irenaljubik.newsapp.di.factory.ViewModelFactory
import com.irenaljubik.newsapp.di.scope.ViewModelKey
import com.irenaljubik.newsapp.ui.common.SearchViewModel
import com.irenaljubik.newsapp.ui.articles.ArticlesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelBinderModule {

    @Binds
    @IntoMap
    @ViewModelKey(ArticlesViewModel::class)
    abstract fun bindArticlesViewModel(articlesViewModel: ArticlesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun bindSearchViewModel(articlesViewModel: SearchViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ConstructorInjectionViewModelFactory): ViewModelFactory

}