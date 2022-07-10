package com.anupdey.app.compose_playground.data.repository

import com.anupdey.app.compose_playground.data.mapper.toWeatherInfo
import com.anupdey.app.compose_playground.data.remote.WeatherApi
import com.anupdey.app.compose_playground.domain.model.weather.WeatherInfo
import com.anupdey.app.compose_playground.domain.repository.WeatherRepository
import com.anupdey.app.compose_playground.util.ApiError
import com.anupdey.app.compose_playground.util.ErrorType
import com.anupdey.app.compose_playground.util.Resource
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
): WeatherRepository {

    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            val response = api.getWeatherData(lat, long).toWeatherInfo()
            Resource.Success(response)
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(ApiError(ErrorType.FAILURE, e.message ?: "An unknown error occurred."))
        }
    }

}