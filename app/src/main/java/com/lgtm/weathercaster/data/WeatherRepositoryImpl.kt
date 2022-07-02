package com.lgtm.weathercaster.data

import com.lgtm.weathercaster.domain.WeatherRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class WeatherRepositoryImpl(
    private val weatherLocalDataSource: WeatherDataSource,
    private val weatherRemoteDataSource: WeatherDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : WeatherRepository {

    // 어떨 때 remote 가져와서 캐시 갱신
    // 어떨 때 local 값 리턴

}