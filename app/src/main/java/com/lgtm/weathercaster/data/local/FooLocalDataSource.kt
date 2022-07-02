package com.lgtm.weathercaster.data.local

import com.lgtm.weathercaster.data.FooDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class FooLocalDataSource(
    private val fooDao: FooDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : FooDataSource {
}