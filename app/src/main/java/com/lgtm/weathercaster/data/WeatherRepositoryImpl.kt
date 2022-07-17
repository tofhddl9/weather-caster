package com.lgtm.weathercaster.data

import android.util.Log
import com.lgtm.weathercaster.domain.vo.WeatherVO
import com.lgtm.weathercaster.di.LocalDataSource
import com.lgtm.weathercaster.di.RemoteDataSource
import com.lgtm.weathercaster.domain.WeatherRepository
import com.lgtm.weathercaster.utils.Response
import java.lang.Exception
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherRepositoryImpl @Inject constructor(
    @LocalDataSource private val weatherLocalDataSource: WeatherDataSource,
    @RemoteDataSource private val weatherRemoteDataSource: WeatherDataSource,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : WeatherRepository {

    override suspend fun getCurrentWeather(
        latitude: Double,
        longitude: Double,
        fetchFromRemote: Boolean
    ): Flow<Response<WeatherVO>> {
        return flow {
            emit(Response.Loading(true))
            val cachedWeather = weatherLocalDataSource.getCurrentWeather(latitude, longitude)
            cachedWeather?.current?.also {
                emit(Response.Success(data = cachedWeather))
            }

            val isCacheEmpty = cachedWeather?.current == null
            val shouldLoadFromCache = !isCacheEmpty && !fetchFromRemote
            if (shouldLoadFromCache) {
                emit(Response.Loading(false))
                return@flow
            }

            var remoteWeather: WeatherVO? = null
            remoteWeather = try {
                weatherRemoteDataSource.getCurrentWeather(latitude, longitude)
            } catch(e: Exception) {
                e.printStackTrace()
                emit(Response.Error(data = remoteWeather,"날씨 정보 업데이트를 실패했습니다."))
                null
            }

            remoteWeather?.also {
                weatherLocalDataSource.clearCurrentWeather()
                weatherLocalDataSource.insertCurrentWeather(it)
                emit(Response.Success(data = it))
            } ?: run {
                emit(Response.Error(data = remoteWeather, message = "날씨 정보 업데이트를 실패했습니다."))
            }

            emit(Response.Loading(false))
        }
    }

}