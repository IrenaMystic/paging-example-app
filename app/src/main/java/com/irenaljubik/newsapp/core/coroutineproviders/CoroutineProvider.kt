package com.irenaljubik.newsapp.core.coroutineproviders

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

interface CoroutineProvider {
    fun createJob(): Job = Job()
    fun createCoroutineScope(): CoroutineScope
    fun mainContext() = Dispatchers.Main.immediate
    fun defaultContext() = Dispatchers.Default
    fun ioContext() = Dispatchers.IO
}