package com.lgtm.weathercaster.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class FooRepositoryImpl(
    private val fooLocalDataSource: FooDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : FooRepository {

}