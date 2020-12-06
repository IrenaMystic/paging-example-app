package com.irenaljubik.newsapp.core.coroutineproviders

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultCoroutineProvider @Inject constructor() : CoroutineProvider {
    override fun createJob(): Job = SupervisorJob()
    override fun createCoroutineScope(): CoroutineScope =
        CoroutineScope(defaultContext() + createJob())
}