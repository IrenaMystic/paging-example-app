package com.irenaljubik.newsapp.di

import android.content.Context
import com.irenaljubik.newsapp.NewsApplication
import com.irenaljubik.newsapp.di.binders.ActivityBinderModule
import com.irenaljubik.newsapp.di.modules.AppModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBinderModule::class,
        AppModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<NewsApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

}