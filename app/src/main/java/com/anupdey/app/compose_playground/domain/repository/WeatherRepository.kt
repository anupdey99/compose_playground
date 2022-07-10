package com.anupdey.app.compose_playground.domain.repository

import com.anupdey.app.compose_playground.domain.model.weather.WeatherInfo
import com.anupdey.app.compose_playground.util.Resource

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}