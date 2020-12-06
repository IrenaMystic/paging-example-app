package com.irenaljubik.newsapp.di.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.irenaljubik.newsapp.di.binders.FragmentsBinderModule
import javax.inject.Inject
import javax.inject.Provider

typealias ViewModelProviderMap = Map<Class<out ViewModel>, Provider<ViewModel>>
typealias ViewModelFactory = ViewModelProvider.Factory

class ConstructorInjectionViewModelFactory @Inject constructor(
    private val creators: @JvmSuppressWildcards ViewModelProviderMap
) : ViewModelFactory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val creator = creators[modelClass] ?: creators.entries.firstOrNull {
            modelClass.isAssignableFrom(it.key)
        }?.value ?: throw  IllegalArgumentException(
            "Unregistered or improperly annotated ViewModel class $modelClass." +
                    "Check to see if the viewmodel is registered in " +
                    "${FragmentsBinderModule::class.simpleName} class"
        )
        try {
            @Suppress("UNCHECKED_CAST")
            return creator.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}