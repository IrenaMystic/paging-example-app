package com.irenaljubik.newsapp.di.binders

import com.irenaljubik.newsapp.MainActivity
import com.irenaljubik.newsapp.di.scope.ActivityScope
import com.irenaljubik.newsapp.di.scope.FragmentScope
import com.irenaljubik.newsapp.ui.articledetails.ArticleDetailsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [CoreBinderModule::class])
abstract class ActivityBinderModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [ViewModelBinderModule::class, FragmentsBinderModule::class])
    abstract fun contributesNewsActivity(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributesArticleDetailsActivity(): ArticleDetailsActivity

}