package com.irenaljubik.newsapp.di.binders

import com.irenaljubik.newsapp.di.scope.FragmentScope
import com.irenaljubik.newsapp.ui.articledetails.ArticleDetailsActivity
import com.irenaljubik.newsapp.ui.articles.ArticlesFragment
import com.irenaljubik.newsapp.ui.categories.CategoryFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentsBinderModule {

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributesCategoryFragment(): CategoryFragment

    @FragmentScope
    @ContributesAndroidInjector
    abstract fun contributesArticleListFragment(): ArticlesFragment

}