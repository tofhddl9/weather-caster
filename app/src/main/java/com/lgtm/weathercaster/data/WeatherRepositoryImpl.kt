package com.lgtm.weathercaster.data

import com.lgtm.weathercaster.domain.WeatherRepository
import com.lgtm.weathercaster.utils.Response
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepositoryImpl @Inject constructor(
    private val weatherLocalDataSource: WeatherDataSource,
    private val weatherRemoteDataSource: WeatherDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : WeatherRepository {

    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
        fetchFromRemote: Boolean
    ): Flow<Response<WeatherVO>> {
        return flow {
            emit(Response.Loading(true))
            val cachedWeather = weatherLocalDataSource.getCurrentWeather()
            emit(Response.Success(data = cachedWeather))

            val remoteWeather = weatherRemoteDataSource.getCurrentWeather()
            remoteWeather?.let {

            }

            emit(Response.Loading(false))
        }
    }

    // 어떨 때 remote 가져와서 캐시 갱신
    // 어떨 때 local 값 리턴

}